package com.epam.Per1.service;

import com.epam.Per1.entity.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(Long id);
    boolean createUser(User user);
    String loginUser(User user, HttpSession session);
    int countAllUsers();
    List<User> getLimitUsers(String where, String groupBy, String sort, int offset, int limit);
    boolean updateUser(User user);
    boolean deleteUser(User user);

}
