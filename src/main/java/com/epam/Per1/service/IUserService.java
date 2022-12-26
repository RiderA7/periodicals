package com.epam.Per1.service;

import com.epam.Per1.entity.User;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public interface IUserService {

    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(Long id);
    boolean createUser(User user);
    String loginUser(User user, HttpSession session);
    boolean updateUser(User user);
    boolean deleteUser(User user);

}
