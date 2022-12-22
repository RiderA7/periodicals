package com.epam.Per1.dao;

import com.epam.Per1.DbException;
import com.epam.Per1.entity.User;

import java.util.List;

public interface UserDao {

    User login(String login, char[] password) throws DbException;

    boolean create(User user) throws DbException;

    boolean isLoginExist(String login) throws DbException;

    User getUserByLogin(String login) throws DbException;

    void changePassword(int userId, char[] newPassword) throws DbException;

    List<User> getAllUsers() throws DbException;

    void changeRole(int userId, int roleId) throws DbException;

    void deleteUser(int userId) throws DbException;

}
