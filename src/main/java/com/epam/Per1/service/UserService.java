package com.epam.Per1.service;

import com.epam.Per1.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByLogin(String login);
    boolean createUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);

}
