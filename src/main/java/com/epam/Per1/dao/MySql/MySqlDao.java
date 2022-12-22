package com.epam.Per1.dao.MySql;

import com.epam.Per1.dao.*;

public class MySqlDao extends DaoFactory {

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

    @Override
    public PublicationDao getPublicationDao() {
        return new MySqlPublicationDao();
    }

    @Override
    public SubscriptionDao getSubscriptionDao() {
        return new MySqlSubscriptionDao();
    }
}
