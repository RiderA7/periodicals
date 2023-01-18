package com.epam.Per1.dao.MySql;

import com.epam.Per1.exception.DbException;
import com.epam.Per1.dao.ConnectionPool;
import com.epam.Per1.dao.PublicationDao;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.entity.Topic;
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

public class MySqlPublicationDao implements PublicationDao {

    private static Logger log = LogManager.getLogger(MySqlPublicationDao.class);
    ConnectionPool connectionPool;

    public MySqlPublicationDao(String propertiesFile) {
        connectionPool = ConnectionPool.getInstance(propertiesFile);
    }

    private static Publication build(ResultSet rs) throws SQLException {
        return new Publication.Builder()
                .setName(rs.getString("publication_title"))
                .setId(rs.getInt("publication_id"))
                .setPrice(rs.getDouble("publication_price"))
                .setTopic(new Topic.Builder()
                        .setId(rs.getInt("publication_topic"))
                        .setName(rs.getString("topic_name"))
                        .getTopic())
                .getPublication();
    }

    @Override
    public int countAll(int topicId) throws DbException {
        int count = 0;
        String byTopic = "";
        if(topicId != 0){
            byTopic = " WHERE publication_topic=" + topicId;
        }
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(SqlUtils.COUNT_ALL_PUBLICATIONS+byTopic)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DbException("Can't count publications", e);
        }
        return count;
    }

    @Override
    public List<Publication> getAll(int topicId) throws DbException {
        List<Publication> publications = new ArrayList<>();
        String byTopic = "";
        if(topicId != 0){
            byTopic = "publication_topic=" + topicId;
        }
        SqlParams sqlParams = new SqlParams.Builder()
                .setWhere(byTopic)
                .getSqlParams();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.GET_ALL_PUBLICATIONS+byTopic);
             ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                publications.add(build(rs));
            }
            return publications;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new DbException("Cannot get ALL publications", e);
        }
    }

    @Override
    public List<Publication> getLimit(SqlParams sqlParams) throws DbException {
        List<Publication> publications = new ArrayList<>();
        String sql = SqlUtils.GET_ALL_PUBLICATIONS;
        sql = Utils.prepareSqlWithPaging(sqlParams, sql);
        System.out.println(sql);
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Publication publication = build(rs);
                publications.add(publication);
            }
        } catch (SQLException e) {
            throw new DbException("Can't get limited list of publications", e);
        }
        return publications;
    }

    @Override
    public Optional<Publication> getById(int id) throws DbException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.GET_PUBLICATION_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return Optional.empty();
                return Optional.ofNullable(build(rs));
            }
        } catch (SQLException e) {
            throw new DbException("Cannot find publication with id '"+id+"'",e);
        }
    }

    @Override
    public Optional<Publication> getByName(String name) throws DbException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.GET_PUBLICATION_BY_NAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) return Optional.empty();
                return Optional.ofNullable(build(rs));
            }
        } catch (SQLException e) {
            throw new DbException("Cannot find publication with title '"+name+"'",e);
        }
    }

    @Override
    public boolean create(Publication publication) throws DbException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.CREATE_PUBLICATION)){
            int k = 0;
            ps.setString(++k, publication.getName());
            ps.setDouble(++k, publication.getPrice());
            ps.setInt(++k, publication.getTopic().getId());
            if (ps.executeUpdate() == 0) {
                throw new DbException("Publication not created!");
            }
        } catch (SQLException e) {
            throw new DbException("Publication not created!",e);
        }
        return true;
    }

    @Override
    public boolean update(Publication publication) throws DbException {
        boolean updated = false;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.UPDATE_PUBLICATION)){
            int k = 0;
            ps.setString(++k, publication.getName());
            ps.setDouble(++k, publication.getPrice());
            ps.setInt(++k, publication.getTopic().getId());
            ps.setInt(++k, publication.getId());
//            System.out.println(ps);
            if (ps.executeUpdate() != 0) {
                updated = true;
            }
        } catch (SQLException e) {
            throw new DbException("Publication not updated! (dao)",e);
        }
        return updated;
    }
}
