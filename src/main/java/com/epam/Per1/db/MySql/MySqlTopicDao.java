package com.epam.Per1.db.MySql;

import com.epam.Per1.DbException;
import com.epam.Per1.db.ConnectionPool;
import com.epam.Per1.db.TopicDao;
import com.epam.Per1.db.model.Topic;
import com.epam.Per1.utils.SqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlTopicDao implements TopicDao {

    private static Topic mapTopic(ResultSet rs) throws SQLException {
        return new Topic.Builder()
                .setName(rs.getString("topic_name"))
                .setId(rs.getInt("topic_id"))
                .getTopic();
    }

    @Override
    public List<Topic> getAllTopics() throws DbException {
        List<Topic> topics = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SqlUtils.GET_ALL_TOPICS);
             ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                topics.add(mapTopic(rs));
            }
            return topics;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new DbException("Cannot get ALL TOPICS", e);
        }
    }
}
