package com.epam.Per1.service.impl;

import com.epam.Per1.DbException;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.User;
import com.epam.Per1.entity.UserRole;
import com.epam.Per1.service.IUserService;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.PagingParams;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

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
    public Optional<User> getUserById(Long id) {
        Optional<User> user;
        try {
            user = DaoFactory.getInstance().getUserDao().getUserById(id);
        } catch (DbException e) {
            log.error("Can't find user with id = " + id, e);
            return Optional.empty();
        }
        return user;
    }

    @Override
    public boolean createUser(User user) {
        boolean created = false;
        try {
            if(DaoFactory.getInstance().getUserDao().create(user)) created = true;
        } catch (DbException e) {
            log.error("Can't create user", e);
        }
        return created;
    }

    @Override
    public String loginUser(User user, HttpSession session) {
        log.info("User "+user.getLogin()+" logged in");
        UserRole userRole;
        try {
            userRole = DaoFactory.getInstance().getUserRoleDao().getUserRole(user.getRoleId());
        } catch (DbException e) {
            userRole = new UserRole.Builder().setId(1L).setUserRole("user").getUserRole();
        }
        log.info("Got role " + userRole.getUserRole());
        session.setAttribute("user", user);
        session.setAttribute("role", userRole);
        String page;
        switch (userRole.getId().intValue()) {
            case 1: page = Pages.USER_ACCOUNT; break;
            case 2: page = Pages.ADMIN_ACCOUNT; break;
            default: page = Pages.LOGIN_PAGE;
        }
        return page;
    }

    @Override
    public boolean updateUser(User user) {
        boolean updated = false;
            try {
                updated = DaoFactory.getInstance().getUserDao().updateUser(user);
            } catch (DbException e) {
                log.error("User not updated! (service)" + e.getMessage());
            }
        return updated;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return DaoFactory.getInstance().getUserDao().getAllUsers();
        } catch (DbException e) {
            log.error("Can't get list of all users from DB!!!");
            return null;
        }
    }

    @Override
    public int countAllUsers() {
        try {
            return DaoFactory.getInstance().getUserDao().countAllUsers();
        } catch (DbException e) {
            log.error("Can't count users in DB!!!");
            return 0;
        }
    }

    @Override
    public List<User> getLimitUsers(String where, String groupBy, String sort, PagingParams pagingParams) {
        int offset = pagingParams.getOffset();
        int limit = pagingParams.getLimit();
        try {
            return DaoFactory.getInstance().getUserDao().getLimitUsers(where, groupBy, sort, offset, limit);
        } catch (DbException e) {
            log.error("Can't get list of users from DB!!!");
            return null;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public void banUser(int id, boolean ban) {
        try {
            Optional<User> optionalUser = DaoFactory.getInstance().getUserDao().getUserById((long) id);
            optionalUser.ifPresent(user -> updateUser(buildUser(user, ban)));
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
                .setBlocked(user.isBlocked() ? 1 : 0)
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
                .setBlocked(user.isBlocked() ? 1 : 0)
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
                .setBlocked(user.isBlocked() ? 1 : 0)
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
                .setBlocked(ban ? 1 : 0)
                .getUser();
    }

}
