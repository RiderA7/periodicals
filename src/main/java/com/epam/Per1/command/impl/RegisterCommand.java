package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.User;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RegisterCommand implements ActionCommand {

    private static Logger log = LogManager.getLogger(RegisterCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        if (login == null || !Validator.validateLogin(login)) {
            log.info("invalid login format was received:" + login);
            req.setAttribute("err", "Login not valid");
            return new CommandResult(Pages.REGISTER_PAGE);
        }

        String password = req.getParameter("password");
        if (password == null || !Validator.validatePassword(password)) {
            log.info("invalid password format was received:" + password);
            req.setAttribute("err", "Password not valid");
            return new CommandResult(Pages.REGISTER_PAGE);
        }

        String confirm = req.getParameter("password2");
        if (!confirm.equals(password)){
            log.info("Passwords not match: " + password + " / " + confirm);
            req.setAttribute("err", "Passwords not match!");
            return new CommandResult((Pages.REGISTER_PAGE));
        }

        String name = req.getParameter("name");
        if (name == null || !Validator.validateName(name)) {
            log.info("invalid name format was received:" + name);
            req.setAttribute("err", "Name not valid");
            return new CommandResult(Pages.REGISTER_PAGE);
        }

        User user;


        return null;
    }
}
