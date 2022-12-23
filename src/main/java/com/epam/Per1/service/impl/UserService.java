package com.epam.Per1.service.impl;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserService implements com.epam.Per1.service.UserService {

    private static Logger log = LogManager.getLogger(UserService.class);

    @Override
    public Optional<User> getUserByLogin(String login) {
        Optional<User> user;
        try {
            user = DaoFactory.getInstance().getUserDao().getUserByLogin(login);
        } catch (DbException e) {
            log.error("Can't find user with login " + login, e);
            return Optional.empty();
        }
        return user;
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
