package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
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

/**
 * Account change password POST-method controller command.
 *
 * @author Alexander Bukhalenkov
 */
public class AccountChangePassPostCommand implements ActionCommand {
    private final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());
    private static Logger log = LogManager.getLogger(AccountChangePassPostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        if (password == null || !Validator.validatePassword(password)) {
            log.info("invalid password format was received:" + password);
            req.getSession().setAttribute("err", "user.register.password.invalid");
            return new CommandResult(Pages.USER_CHANGE_PASSWORD);
        }

        String confirm = req.getParameter("password2");
        if (!confirm.equals(password)) {
            log.info("Passwords not match: " + password + " / " + confirm);
            req.getSession().setAttribute("err", "user.register.passwords.not.match");
            return new CommandResult((Pages.USER_CHANGE_PASSWORD));
        }
        boolean updated = false;
        User newUser = null;
        try {
            User user = (User) req.getSession().getAttribute("user");
            newUser = userService.buildUser(user, Utils.hash(req.getParameter("password").toCharArray()));
            updated = userService.update(newUser);
        } catch (NullPointerException e) {
            log.error("No user logged in for update!");
        }
        if (updated) {
            req.getSession().setAttribute("user", newUser);
            return new CommandResult(Pages.USER_PROFILE, true);
        } else return new CommandResult(Pages.USER_CHANGE_PASSWORD);
    }

}
