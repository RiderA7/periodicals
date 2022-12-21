package com.epam.Per1.dao;

import com.epam.Per1.dao.MySql.MySqlDao;

public abstract class DaoFactory {

    private static DaoFactory instance;

    public synchronized static DaoFactory getInstance(){
        if(instance == null) {
            instance = new MySqlDao();
        }
        return instance;
    }

    public abstract TopicDao getTopicDao();
    public abstract UserDao getUserDao();
    public abstract UserRoleDao getUserRoleDao();
}
