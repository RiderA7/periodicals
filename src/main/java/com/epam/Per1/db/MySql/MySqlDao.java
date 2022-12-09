package com.epam.Per1.db.MySql;

import com.epam.Per1.db.Dao;
import com.epam.Per1.db.TopicDao;
import com.epam.Per1.db.UserDao;

public class MySqlDao extends Dao {

    @Override
    public TopicDao getTopicDao() {
        return new MySqlTopicDao();
    }

    @Override
    public UserDao getUserDao() {
        return null;
    }
}
