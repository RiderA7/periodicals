package com.epam.Per1.service.impl;

import com.epam.Per1.exception.DbException;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dao.UserDao;
import com.epam.Per1.dao.UserRoleDao;
import com.epam.Per1.entity.User;
import com.epam.Per1.entity.UserRole;
import com.epam.Per1.service.IService;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.SqlParams;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserService implements IService<User> {

    private static Logger log = LogManager.getLogger(UserService.class);
    private UserDao userDao;
    private UserRoleDao userRoleDao = DaoFactory.getInstance().getUserRoleDao();

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        Optional<User> user;
        try {
            user = userDao.getUserByLogin(login);
        } catch (DbException e) {
            log.error("Can't find user with login " + login, e);
            return Optional.empty();
        }
        return user;
    }

    @Override
    public Optional<User> getById(int id) {
        Optional<User> user;
        try {
            user = userDao.getUserById(id);
        } catch (DbException e) {
            log.error("Can't find user with id = " + id, e);
            return Optional.empty();
        }
        return user;
    }

    @Override
    public boolean create(User user) {
        boolean created = false;
        try {
            if(userDao.create(user)) created = true;
        } catch (DbException e) {
            log.error("Can't create user", e);
        }
        return created;
    }

    public String login(User user, HttpSession session) {
        log.info("User "+user.getLogin()+" logged in");
        UserRole userRole;
        try {
            userRole = userRoleDao.getUserRole(user.getRoleId());
        } catch (DbException e) {
            userRole = new UserRole.Builder().setId(1).setUserRole("USER").getUserRole();
        }
        log.info("Got role " + userRole.getUserRole());
        session.setAttribute("user", user);
        session.setAttribute("role", userRole);
        String page;
        switch (userRole.getId()) {
            case 1: page = Pages.USER_ACCOUNT; break;
            case 2: page = Pages.ADMIN_ACCOUNT; break;
            default: page = Pages.LOGIN_PAGE;
        }
        return page;
    }

    @Override
    public boolean update(User user) {
        boolean updated = false;
            try {
                updated = userDao.updateUser(user);
            } catch (DbException e) {
                log.error("User not updated! (service)" + e.getMessage());
            }
        return updated;
    }

    @Override
    public List<User> getAll() {
        try {
            return userDao.getAllUsers();
        } catch (DbException e) {
            log.error("Can't get list of all users from DB!!!");
            return null;
        }
    }

    @Override
    public int countAll() {
        try {
            return userDao.countAllUsers();
        } catch (DbException e) {
            log.error("Can't count users in DB!!!");
            return 0;
        }
    }

    @Override
    public List<User> getLimit(SqlParams sqlParams) {
        try {
            return userDao.getLimitUsers(sqlParams);
        } catch (DbException e) {
            log.error("Can't get list of users from DB!!!");
            return null;
        }
    }

    public boolean delete(User user) {
        return false;
    }

    public void ban(int id, boolean ban) {
        try {
            Optional<User> optionalUser = userDao.getUserById(id);
            optionalUser.ifPresent(user -> update(buildUser(user, ban)));
        } catch (DbException e) {
            log.error("Can't ban/unban userId="+id);
            e.printStackTrace();
        }
    }

    public User buildUser(User user, String login, String name){
        return new User.Builder()
                .setId(user.getId())
                .setLogin(login)
                .setName(name)
                .setPassword(user.getPassword())
                .setRoleId(user.getRoleId())
                .setMoney((int) user.getMoney() * 100)
                .setIsBlocked(user.isBlocked() ? 1 : 0)
                .getUser();
    }
    public User buildUser(User user, String password){
        return new User.Builder()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setName(user.getName())
                .setPassword(password)
                .setRoleId(user.getRoleId())
                .setMoney((int) user.getMoney() * 100)
                .setIsBlocked(user.isBlocked() ? 1 : 0)
                .getUser();
    }
    public User buildUser(User user, int deposit){
        return new User.Builder()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setRoleId(user.getRoleId())
                .setMoney((int) (user.getMoney() + deposit) * 100)
                .setIsBlocked(user.isBlocked() ? 1 : 0)
                .getUser();
    }
    public User buildUser(User user, boolean ban){
        return new User.Builder()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setRoleId(user.getRoleId())
                .setMoney((int) user.getMoney() * 100)
                .setIsBlocked(ban ? 1 : 0)
                .getUser();
    }

}
