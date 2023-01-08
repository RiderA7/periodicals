package com.epam.Per1.dao;

import com.epam.Per1.DbException;
import com.epam.Per1.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<K, T extends Entity> {

    protected Connection connection;

    public abstract List<T> findAll() throws DbException;

    public abstract Optional<T> getById(K id) throws DbException;

    public abstract boolean create(T entity) throws DbException;

    public abstract boolean update(T entity) throws DbException;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            //...
        }
    }
}
