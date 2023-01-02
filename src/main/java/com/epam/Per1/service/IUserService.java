package com.epam.Per1.service;

import com.epam.Per1.entity.User;
import com.epam.Per1.utils.PagingParams;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(Long id);
    boolean createUser(User user);
    String loginUser(User user, HttpSession session);
    int countAllUsers();
    List<User> getAllUsers();
    List<User> getLimitUsers(String where, String groupBy, String sort, PagingParams pagingParams);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    void banUser(int id, boolean ban);

    User buildUser(User user, String login, String name);
    User buildUser(User user, String password);
    User buildUser(User user, int deposit);
    User buildUser(User user, boolean ban);

}
