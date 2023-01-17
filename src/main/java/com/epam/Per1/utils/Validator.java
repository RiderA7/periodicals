package com.epam.Per1.utils;

import java.util.regex.Pattern;

public class Validator {

    private final static String REGEX_CHECK_FOR_LOGIN_AS_EMAIL = "^([\\w\\-\\.]+)@([\\w\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private final static String REGEX_CHECK_FOR_PASSWORD = "^([\\wа-яА-Я]{7,20})$";
    private final static String REGEX_CHECK_FOR_NAME = "^[a-zA-Zа-яА-Я\\\\s]{2,20}$";
    private final static String REGEX_CHECK_FOR_PUBLICATION_NAME = "^[a-zA-Zа-яА-Я0-9\\\\s\\ ]{2,}$";

    public static boolean validateLogin(String login) {
        return Pattern.matches(REGEX_CHECK_FOR_LOGIN_AS_EMAIL, login);
    }

    public static boolean validatePassword(String password) {
        return Pattern.matches(REGEX_CHECK_FOR_PASSWORD, password);
    }

    public static boolean validateName(String name) {
        return Pattern.matches(REGEX_CHECK_FOR_NAME, name);
    }

    public static boolean validateDeposit(int deposit){
        boolean test = false;
        if(deposit>=1){
            test = true;
        }
        return test;
    }

    public static boolean validatePublicationName(String publicationName) {
        return Pattern.matches(REGEX_CHECK_FOR_PUBLICATION_NAME, publicationName);
    }
}
