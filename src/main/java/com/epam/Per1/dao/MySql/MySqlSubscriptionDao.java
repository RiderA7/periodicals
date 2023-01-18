package com.epam.Per1.dao.MySql;

import com.epam.Per1.exception.DbException;
import com.epam.Per1.dao.ConnectionPool;
import com.epam.Per1.dao.SubscriptionDao;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.entity.Subscription;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.entity.User;
import com.epam.Per1.utils.SqlParams;
import com.epam.Per1.utils.SqlUtils;
import com.epam.Per1.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlSubscriptionDao implements SubscriptionDao {

    private static Logger log = LogManager.getLogger(MySqlSubscriptionDao.class);
    ConnectionPool connectionPool;

    public MySqlSubscriptionDao(String propertiesFile) {
        connectionPool = ConnectionPool.getInstance(propertiesFile);
    }

    private static Subscription build(ResultSet rs) throws SQLException {
        return new Subscription.Builder()
                .setId(rs.getInt("subscription_id"))
                .setUser(new User.Builder()
                        .setId(rs.getInt("user_id"))
                        .setName(rs.getString("user_name"))
                        .getUser())
                .setPublication(new Publication.Builder()
                        .setId(rs.getInt("publication_id"))
                        .setName(rs.getString("publication_title"))
                        .setPrice(rs.getDouble("publication_price"))
                        .setTopic(new Topic.Builder()
                                .setId(rs.getInt("topic_id"))
                                .setName(rs.getString("topic_name"))
                                .getTopic())
                        .getPublication())
                .getSubscription();
    }

    @Override
    public int countAll(SqlParams sqlParams) throws DbException {
        String sql = Utils.prepareSqlWithPaging(sqlParams, SqlUtils.COUNT_ALL_SUBSCRIPTIONS);
        int count = 0;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DbException("Can't count subscriptions", e);
        }
        return count;
    }

    @Override
    public List<Subscription> getAll(SqlParams sqlParams) throws DbException {
        return getLimit(sqlParams);
    }

    @Override
    public List<Subscription> getLimit(SqlParams sqlParams) throws DbException {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = SqlUtils.GET_ALL_SUBSCRIPTIONS;
        sql = Utils.prepareSqlWithPaging(sqlParams, sql);
        System.out.println(sql);
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Subscription subscription = build(rs);
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            throw new DbException("Can't get limited list of subscriptions", e);
        }
        return subscriptions;
    }

    @Override
    public Optional<Subscription> getById(int id) throws DbException {
        SqlParams sqlParams = new SqlParams.Builder()
                .setWhere("subscription_id=" + id)
                .getSqlParams();
        List<Subscription> subscriptions = getLimit(sqlParams);
        if(subscriptions.size() > 0) return Optional.ofNullable(subscriptions.get(0));
        return Optional.empty();
    }

    @Override
    public boolean create(Subscription subscription) throws DbException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.CREATE_SUBSCRIPTION)){
            int k = 0;
            ps.setInt(++k, subscription.getUser().getId());
            ps.setInt(++k, subscription.getPublication().getId());
            ps.setInt(++k, subscription.getStatus());
//            ps.setDate(++k, new Date().getTime());
            if (ps.executeUpdate() == 0) {
                throw new DbException("Publication not created!");
            }
        } catch (SQLException e) {
            throw new DbException("Publication not created!",e);
        }
        return true;
    }

    @Override
    public boolean update(Subscription subscription) throws DbException {
        return false;
    }
}
