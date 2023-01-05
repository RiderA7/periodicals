package com.epam.Per1.dao;

import com.epam.Per1.DbException;
import com.epam.Per1.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    int countAllUsers() throws DbException;
    List<User> getAllUsers() throws DbException;
    List<User> getLimitUsers(String where, String groupBy, String sort, int offset, int limit) throws DbException;
    Optional<User> login(String login, char[] password) throws DbException;
    boolean isLoginExist(String login) throws DbException;
    boolean create(User user) throws DbException;
    Optional<User> getUserByLogin(String login) throws DbException;
    Optional<User> getUserById(Long id) throws DbException;
    boolean updateUser(User user) throws DbException;
    void deleteUser(int userId) throws DbException;
    void changeRole(int userId, int roleId) throws DbException;

}
