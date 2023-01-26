package com.epam.Per1.utils;

public class SqlUtils {

    public static final String GET_ALL_TOPICS = "SELECT topic_id, topic_name, count(publication_id) as pubs " +
            "FROM topic LEFT JOIN publication ON topic_id=publication_topic" +
            " GROUP BY topic_name";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM user JOIN role " +
            "ON user_role=role_id WHERE user_login LIKE ?";
    public static final String LOGIN = "SELECT * FROM user JOIN role " +
            "ON user_role=role_id WHERE user_login LIKE ? AND user_password_md5 LIKE ?";
    public static final String GET_ALL_USER_ROLES = "SELECT * FROM role";
    public static final String GET_USER_ROLE_BY_ID = "SELECT * FROM role WHERE role_id=?";
    public static final String CREATE_USER
            = "INSERT INTO user (user_login, user_name, user_password_md5, user_role)" +
            " VALUES (?, ?, ?, ?)";
    public static final String UPDATE_USER =
            "UPDATE user SET" +
                    " user_login=?," +
                    " user_name=?," +
                    " user_password_md5=?," +
                    " user_role=?," +
                    " user_money=?," +
                    " user_blocked=?" +
                    " WHERE user_id=?";
    public static final String FIND_USER_BY_ID = "SELECT * FROM user JOIN role " +
            "ON user_role=role_id WHERE user_id=?";
    public static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM user";
    public static final String SELECT_LIMIT_USERS = "SELECT * FROM user JOIN role ON user_role=role_id";
    public static final String CREATE_TOPIC = "INSERT INTO topic (topic_name) VALUES (?)";
    public static final String GET_TOPIC_BY_ID = "SELECT * FROM topic WHERE topic_id=?";
    public static final String GET_TOPIC_BY_NAME = "SELECT * FROM topic WHERE topic_name=?";
    public static final String UPDATE_TOPIC = "UPDATE topic SET" +
            " topic_name=? WHERE topic_id=?";
    public static final String COUNT_ALL_TOPICS = "SELECT COUNT(*) FROM topic";
    public static final String COUNT_ALL_PUBLICATIONS = "SELECT COUNT(*) FROM publication";
    public static final String GET_ALL_PUBLICATIONS = "SELECT * " +
            "FROM publication left join topic on publication_topic=topic_id";
    public static final String GET_PUBLICATION_BY_ID = "SELECT * " +
            "FROM publication left join topic on publication_topic=topic_id WHERE publication_id=?";
    public static final String GET_PUBLICATION_BY_NAME = "SELECT * " +
            "FROM publication left join topic on publication_topic=topic_id WHERE publication_title=?";
    public static final String CREATE_PUBLICATION = "INSERT INTO publication " +
            "(publication_title, publication_price, publication_topic) VALUES (?, ?, ?)";
    public static final String UPDATE_PUBLICATION = "UPDATE publication SET" +
            " publication_title=?, publication_price=?, publication_topic=? WHERE publication_id=?";
    public static final String GET_ALL_SUBSCRIPTIONS = "SELECT " +
            "subscription_id, user_id, user_login, user_name, user_role, role_name, user_money, publication_id, publication_title, publication_price, topic_id, topic_name, status " +
            "FROM subscription " +
            "JOIN user ON user = user_id " +
            "JOIN role ON role_id = user_role " +
            "JOIN publication ON publication = publication_id " +
            "JOIN topic ON publication_topic = topic_id";
    public static final String COUNT_ALL_SUBSCRIPTIONS = "SELECT COUNT(*) FROM subscription";

    public static final String CREATE_SUBSCRIPTION = "INSERT INTO subscription " +
            "(user, publication, status) VALUES (?, ?, ?)";
    public static final String UPDATE_SUBSCRIPTION = "UPDATE subscription SET" +
            " user=?, publication=?, status=? WHERE subscription_id=?";
}
