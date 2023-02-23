package com.epam.Per1.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection pool for DB.
 *
 * @author Alexander Bukhalenkov
 */
public class ConnectionPool {

    private static ConnectionPool instance = null;
    private static DataSource dataSource = null;

    private ConnectionPool(String properties){
        dataSource = MyDataSourceHikari.getDataSource(properties);
    }

    public synchronized static ConnectionPool getInstance(String properties){
        if(instance == null)
            instance = new ConnectionPool(properties);
        return instance;
    }

    public Connection getConnection(){
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot init DBManager", e);
        }
        return con;
    }
}
