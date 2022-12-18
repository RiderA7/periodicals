package com.epam.Per1.db;

import com.epam.Per1.DbException;
import com.epam.Per1.db.Entity.Topic;

import java.util.List;

public interface TopicDao {

    List<Topic> getAllTopics() throws DbException;
}
