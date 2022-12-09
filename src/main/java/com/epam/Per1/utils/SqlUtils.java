package com.epam.Per1.utils;

public class SqlUtils {

    public static final String GET_ALL_TOPICS = "SELECT * FROM topic";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE user_login LIKE ?";
}
