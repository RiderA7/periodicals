package com.epam.Per1.dao.MySql;

import com.epam.Per1.dao.Dao;
import com.epam.Per1.dao.TopicDao;
import com.epam.Per1.dao.UserDao;
import com.epam.Per1.dao.UserRoleDao;

public class MySqlDao extends Dao {

    @Override
    public TopicDao getTopicDao() {
        return new MySqlTopicDao();
    }

    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao();
    }

    @Override
    public UserRoleDao getUserRoleDao() {
        return new MySqlUserRoleDao();
    }
}
