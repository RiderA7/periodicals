package com.epam.Per1.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String hash(char[] text){
        StringBuilder hashed = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(new String(text).getBytes(StandardCharsets.UTF_8));
            for (byte b : hash){
                hashed.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashed.toString();
    }

    public static String getErrMessage(Exception e) {
        return e + "; Caused by: " + e.getCause().toString();
    }

    public static String prepareSqlWithPaging(SqlParams sqlParams, String sql) {
        if(!sqlParams.getWhere().equals("")) sql += sqlParams.getWhere();
        if(!sqlParams.getGroupBy().equals("")) sql += sqlParams.getGroupBy();
        if(!sqlParams.getSort().equals("")) sql += sqlParams.getSort();
        if(!sqlParams.getLimits().equals("")) sql += sqlParams.getLimits();
        return sql;
    }

}
