package com.epam.Per1.dao.MySql;

import com.epam.Per1.dao.*;

public class MySqlDao extends DaoFactory {

    private static String PROPERTIES_FILE;

    public MySqlDao(String propertiesFile) {
        PROPERTIES_FILE = propertiesFile;
    }

    @Override
    public TopicDao getTopicDao() {
        return new MySqlTopicDao(PROPERTIES_FILE);
    }

    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao(PROPERTIES_FILE);
    }

    @Override
    public UserRoleDao getUserRoleDao() {
        return new MySqlUserRoleDao(PROPERTIES_FILE);
    }

    @Override
    public PublicationDao getPublicationDao() {
        return new MySqlPublicationDao(PROPERTIES_FILE);
    }

    @Override
    public SubscriptionDao getSubscriptionDao() {
        return new MySqlSubscriptionDao(PROPERTIES_FILE);
    }
}
