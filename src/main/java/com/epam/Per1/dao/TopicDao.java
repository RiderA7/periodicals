package com.epam.Per1.dao;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.entity.Topic;

import java.util.List;

public interface TopicDao {

    List<Topic> getAllTopics() throws DbException;
}
