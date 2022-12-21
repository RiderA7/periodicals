package com.epam.Per1.command.impl;

import com.epam.Per1.AppException;
import com.epam.Per1.DbException;
import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.User;
import com.epam.Per1.entity.UserRole;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoginCommand implements ActionCommand {

    private static Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        char[] password = req.getParameter("password").toCharArray();

        if (login.isEmpty() || password.length == 0) {
            String error = "empty login or password";
            log.info(error);
            req.setAttribute("err", "You must enter login and password");
//            resp.sendRedirect(req.getContextPath() + "/account/login");
//            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return new CommandResult(Pages.LOGIN_PAGE);
        }

        try {
            User user = DaoFactory.getInstance().getUserDao().login(login, password);
            if(user == null) {
                String error = "User "+login+" not found";
                log.info(error);
                req.setAttribute("err", "Wrong login or password!");
//                resp.sendRedirect(req.getContextPath() + "/account/login");
//                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                return new CommandResult(Pages.LOGIN_PAGE);
            } else {
                log.info("User "+login+" logged in");
                UserRole userRole = DaoFactory.getInstance().getUserRoleDao().getUserRole(user.getRoleId());
                log.info("Got role " + userRole.getUserRole());
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("role", userRole);
                String page;
                switch (userRole.getId().intValue()) {
//                    case 1: page = Pages.LOGIN_PAGE; break;
                    case 2: page = Pages.USER_ACCOUNT; break;
                    case 3: page = Pages.ADMIN_ACCOUNT; break;
                    default: page = Pages.LOGIN_PAGE;
                }
                return new CommandResult(page, true);
            }
        } catch (DbException e) {
            log.error(Utils.getErrMessage(e));
            throw new AppException(e);
        }
    }
}
