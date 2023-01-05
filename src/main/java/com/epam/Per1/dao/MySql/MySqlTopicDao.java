package com.epam.Per1.dao.MySql;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.ConnectionPool;
import com.epam.Per1.dao.TopicDao;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.utils.SqlUtils;
import com.epam.Per1.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlTopicDao implements TopicDao {

    private static Topic buildTopic(ResultSet rs) throws SQLException {
        return new Topic.Builder()
                .setName(rs.getString("topic_name"))
                .setId(rs.getInt("topic_id"))
                .setPubs(rs.getInt("pubs"))
                .getTopic();
    }

    @Override
    public List<Topic> getAllTopics() throws DbException {
        List<Topic> topics = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.GET_ALL_TOPICS);
             ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                topics.add(buildTopic(rs));
            }
            return topics;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new DbException("Cannot get ALL TOPICS", e);
        }
    }

    @Override
    public List<Topic> getLimitTopics(String where, String groupBy, String sort, int offset, int limit) throws DbException {
        List<Topic> topics = new ArrayList<>();
        String limitStr = "";
        String sql = SqlUtils.GET_ALL_TOPICS;
        sql = Utils.prepareSqlWhithPaging(where, groupBy, sort, offset, limit, limitStr, sql);
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Topic topic = buildTopic(rs);
                topics.add(topic);
            }
        } catch (SQLException e) {
            throw new DbException("Can't get limited list of topics", e);
        }
        return topics;
    }

    @Override
    public boolean create(Topic topic) throws DbException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.CREATE_TOPIC)){
            int k = 0;
            ps.setString(++k, topic.getName());
            if (ps.executeUpdate() == 0) {
                throw new DbException("Topic not created!");
            }
        } catch (SQLException e) {
            throw new DbException("Topic not created!",e);
        }
        return true;
    }

    @Override
    public Optional<Topic> getTopicById(int id) throws DbException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.GET_TOPIC_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return Optional.empty();
                return Optional.ofNullable(buildTopic(rs));
            }
        } catch (SQLException e) {
            throw new DbException("Cannot find topic with id '"+id+"'",e);
        }
    }

    public Optional<Topic> getTopicByName(String name) throws DbException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.GET_TOPIC_BY_NAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return Optional.empty();
                return Optional.ofNullable(buildTopic(rs));
            }
        } catch (SQLException e) {
            throw new DbException("Cannot find topic with name '"+name+"'",e);
        }
    }

    @Override
    public boolean updateTopic(Topic topic) throws DbException {
        boolean updated = false;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.UPDATE_TOPIC)){
            int k = 0;
            ps.setString(++k, topic.getName());
            ps.setLong(++k, topic.getId());
//            System.out.println(ps);
            if (ps.executeUpdate() != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            throw new DbException("Topic not updated! (dao)",e);
        }
        return updated;
    }

    @Override
    public int countAllTopics() throws DbException {
        int count = 0;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.COUNT_ALL_TOPICS)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DbException("Can't count topics", e);
        }
        return count;
    }

}
