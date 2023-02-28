package com.epam.Per1.dao;

import com.epam.Per1.exception.DbException;
import com.epam.Per1.entity.User;
import com.epam.Per1.utils.SqlParams;

import java.util.List;
import java.util.Optional;

/**
 * The UserDao interface defines the methods that must be implemented by classes that provide data access
 * functionality for User objects.
 * It declares methods for retrieving, creating, updating, and deleting User objects from the database,
 * as well as for performing login authentication and checking if a login exists in the database. *
 *
 * @author Alexander Bukhalenkov
 */
public interface UserDao {
    int countAllUsers() throws DbException;
    List<User> getAllUsers() throws DbException;
    List<User> getLimitUsers(SqlParams sqlParams) throws DbException;
    Optional<User> login(String login, char[] password) throws DbException;
    boolean isLoginExist(String login) throws DbException;
    boolean create(User user) throws DbException;
    Optional<User> getUserByLogin(String login) throws DbException;
    Optional<User> getUserById(int id) throws DbException;
    boolean updateUser(User user) throws DbException;
    void deleteUser(int userId) throws DbException;
    void changeRole(int userId, int roleId) throws DbException;
}
