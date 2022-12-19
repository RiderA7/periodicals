package com.epam.Per1.dao;

import com.epam.Per1.dao.MySql.MySqlDao;

public abstract class Dao {

    public static Dao getDao(){
        return new MySqlDao();
    }

    public abstract TopicDao getTopicDao();
    public abstract UserDao getUserDao();
}
