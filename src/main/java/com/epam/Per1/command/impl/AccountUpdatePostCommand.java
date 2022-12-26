package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AccountUpdatePostCommand implements ActionCommand {
    private final UserService userService = new UserService();
    private static Logger log = LogManager.getLogger(AccountUpdatePostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login == null || !Validator.validateLogin(login)) {
            log.info("invalid login format was received:" + login);
            req.setAttribute("err", "Login not valid");
            return new CommandResult(Pages.USER_UPDATE);
        }
        String name = req.getParameter("name");
        if (name == null || !Validator.validateName(name)) {
            log.info("invalid name format was received:" + name);
            req.setAttribute("err", "Name not valid");
            return new CommandResult(Pages.USER_UPDATE);
        }
        boolean updated = false;
        User newUser = null;
        try {
            User user = (User) req.getSession().getAttribute("user");
            newUser = buildUser(user, req);
            updated = userService.updateUser(newUser);
        } catch (NullPointerException e) {
            log.error("No user logged in for update!");
        }
        if (updated) {
            req.getSession().setAttribute("user", newUser);
            return new CommandResult(Pages.USER_PROFILE);
        } else return new CommandResult(Pages.USER_UPDATE);
    }

    private User buildUser(User user, HttpServletRequest req) {
        return new User.Builder()
                .setId(user.getId())
                .setLogin(req.getParameter("login"))
                .setName(req.getParameter("name"))
                .setPassword(user.getPassword())
                .setRoleId(user.getRoleId())
                .setMoney((int) user.getMoney() * 100)
                .setBlocked(user.isBlocked() ? 1 : 0)
                .getUser();
    }
}
