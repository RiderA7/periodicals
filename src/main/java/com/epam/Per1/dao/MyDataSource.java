package com.epam.Per1.dao;

import com.epam.Per1.exception.DbException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MyDataSource {

    private static DataSource dataSource;

    private MyDataSource() {
    }

    public static synchronized DataSource getDataSource() throws DbException {
        if(dataSource != null) return dataSource;
        Context context = null;
        try {
            context = new InitialContext();
            dataSource =
                    (DataSource) context.lookup("java:comp/env/jdbc/periodicals");
        } catch (NamingException e) {
            throw new DbException("Can't find context: ", e);
        }
        return dataSource;
    }

}
