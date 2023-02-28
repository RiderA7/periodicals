package com.epam.Per1.dao;

import com.epam.Per1.exception.DbException;
import com.epam.Per1.entity.Subscription;
import com.epam.Per1.utils.SqlParams;

import java.util.List;
import java.util.Optional;

/**
 * The SubscriptionDao interface provides methods for accessing and manipulating Subscription objects in a database.
 * It defines methods for counting and retrieving Subscription objects, as well as creating and updating them.
 *
 * @author Alexander Bukhalenkov
 */
public interface SubscriptionDao {
    int countAll(SqlParams sqlParams) throws DbException;
    List<Subscription> getAll(SqlParams sqlParams) throws DbException;
    List<Subscription> getLimit(SqlParams sqlParams) throws DbException;
    Optional<Subscription> getById(int id) throws DbException;
    boolean create(Subscription subscription) throws DbException;
    boolean update(Subscription subscription) throws DbException;
}
