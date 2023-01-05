package com.epam.Per1.dao;

import com.epam.Per1.DbException;
import com.epam.Per1.entity.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicDao {

    int countAllTopics() throws DbException;
    List<Topic> getAllTopics() throws DbException;
    List<Topic> getLimitTopics(String where, String groupBy, String sort, int offset, int limit) throws DbException;
    Optional<Topic> getTopicById(int id) throws DbException;
    Optional<Topic> getTopicByName(String name) throws DbException;
    boolean create(Topic topic) throws DbException;
    boolean updateTopic(Topic topic) throws DbException;
}
