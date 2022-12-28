package com.epam.Per1.utils;

public class SqlUtils {

    public static final String GET_ALL_TOPICS = "SELECT * FROM topic";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE user_login LIKE ?";
    public static final String LOGIN
            = "SELECT * FROM user WHERE user_login LIKE ? AND user_password_md5 LIKE ?";
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
    public static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE user_id=?";
    public static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM user";
    public static final String SELECT_LIMIT_USERS = "SELECT * FROM user ? ? ? LIMIT ?, ?";
}
