package com.epam.Per1.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static ConnectionPool instance = null;

    private ConnectionPool(){}

    public synchronized static ConnectionPool getInstance(){
        if(instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection(){
        Context context;
        Connection con = null;
        try {
            context = new InitialContext();
            DataSource ds =
                    (DataSource) context.lookup("java:comp/env/jdbc/periodicals");
            con = ds.getConnection();
        } catch (NamingException | SQLException e) {
            throw new IllegalStateException("Cannot init DBManager", e);
//            e.printStackTrace();
        }
        return con;
    }
}
