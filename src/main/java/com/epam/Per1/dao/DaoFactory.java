package com.epam.Per1.dao;

import com.epam.Per1.dao.MySql.MySqlDao;

public abstract class DaoFactory {

    private static DaoFactory instance;
    private static final String PROPERTIES_FILE = "datasource.properties";

    public synchronized static DaoFactory getInstance(){
        if(instance == null) {
            instance = new MySqlDao(PROPERTIES_FILE);
        }
        return instance;
    }

    public abstract TopicDao getTopicDao();
    public abstract UserDao getUserDao();
    public abstract UserRoleDao getUserRoleDao();
    public abstract PublicationDao getPublicationDao();
    public abstract SubscriptionDao getSubscriptionDao();
}
