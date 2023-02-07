package com.epam.Per1.service.impl;

import com.epam.Per1.dao.UserDao;
import com.epam.Per1.entity.User;
import com.epam.Per1.entity.UserRole;
import com.epam.Per1.exception.DbException;
import com.epam.Per1.exception.NoSuchElementException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    public void getByLoginPositiveTest(){
        UserDao userDao = mock(UserDao.class);
        User user = new User(1, "user", "", "", new UserRole(1, "user"), 0.0, false);
        Optional<User> optionalUser =
                Optional.of(user);
        when(userDao.getUserByLogin("user")).thenReturn(optionalUser);

        UserService userService = new UserService(userDao);

        assertEquals(user, userService.getByLogin("user"));
    }

    @Test
    public void getByLoginNegativeTest(){
        UserDao userDao = mock(UserDao.class);
        User user = new User(1, "user", "", "", new UserRole(1, "user"), 0.0, false);
        Optional<User> optionalUser =
                Optional.of(user);
        when(userDao.getUserByLogin("user1")).thenThrow(new DbException("error"));

        UserService userService = new UserService(userDao);

        assertThrows(NoSuchElementException.class, () -> userService.getByLogin("user1"));
    }
}