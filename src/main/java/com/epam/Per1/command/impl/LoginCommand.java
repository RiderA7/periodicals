package com.epam.Per1.command.impl;

import com.epam.Per1.exception.AppException;
import com.epam.Per1.exception.DbException;
import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

/**
 * Login user POST-method controller command.
 * Gets login and password values from the request.
 * Validates this values, if input data is not valid, or no such user is presented in the database (user is null),
 * returns router to the same page with message about incorrect login or password.
 * Otherwise, finds the user by this values and sets sessions attributes and
 * returns router to the welcome page.
 *
 * @author Alexander Bukhalenkov
 */
public class LoginCommand implements ActionCommand {

    private static Logger log = LogManager.getLogger(LoginCommand.class);
    UserService userService = new UserService(DaoFactory.getInstance().getUserDao());

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        char[] password = req.getParameter("password").toCharArray();

        if (login.isEmpty() || password.length == 0) {
            String error = "empty login or password";
            log.info(error);
            req.getSession().setAttribute("err", "login.must.be.not.empty");
//            resp.sendRedirect(req.getContextPath() + "/account/login");
//            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return new CommandResult(Pages.LOGIN_PAGE);
        }

        try {
            String page;
            boolean redirect;
            Optional<User> userOptional = userService.login(login, password);
            if (userOptional.isEmpty()) {
                String error = "User " + login + " not found";
                log.info(error);
                req.getSession().setAttribute("err", "login.must.be.valid");
                page = Pages.LOGIN_PAGE;
                redirect = false;
            } else {
                if (userOptional.get().isBlocked()) {
                    log.info("User " + userOptional.get().getName() + " BANNED!");
                    req.getSession().setAttribute("err", "login.account.banned");
                    return new CommandResult(Pages.LOGIN_PAGE);
                }
                page = userService.setUserLogin(userOptional.get(), req.getSession());
                redirect = true;
            }
            return new CommandResult(page, redirect);
        } catch (DbException e) {
            log.error(Utils.getErrMessage(e));
            throw new AppException(e);
        }
    }
}
