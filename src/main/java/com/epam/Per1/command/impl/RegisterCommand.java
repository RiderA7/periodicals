package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.Utils;
import com.epam.Per1.utils.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class RegisterCommand implements ActionCommand {

    private final UserService userService = new UserService();

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
        if (!confirm.equals(password)) {
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

        User user = buildUser(req);
        if (userService.create(user)) {
            Optional<User> userOptional = userService.getByName(user.getLogin());
            if (userOptional.isPresent()) {
                String page = userService.login(userOptional.get(), req.getSession());
                return new CommandResult(page, true);
            }
        }
        log.info("Something wrong during registration!");
        req.setAttribute("err", "Registration maybe not success. Ask Administrator");
        return new CommandResult(Pages.REGISTER_PAGE);
    }

    private User buildUser(HttpServletRequest req) {
        return new User.Builder()
                .setLogin(req.getParameter("login"))
                .setPassword(Utils.hash(req.getParameter("password").toCharArray()))
                .setName(req.getParameter("name"))
                .setRoleId(1)
                .getUser();
    }
}
