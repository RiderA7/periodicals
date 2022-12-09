package com.epam.Per1.db;

import com.epam.Per1.DbException;
import com.epam.Per1.db.model.Topic;

import java.util.List;

public interface TopicDao {

    List<Topic> getAllTopics() throws DbException;
}
