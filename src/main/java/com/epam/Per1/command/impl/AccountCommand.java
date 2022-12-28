package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.User;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AccountCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AccountCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        log.info("User in session found:" + user);
        if(user != null) {
            req.setAttribute("user", user);
            return new CommandResult(Pages.USER_ACCOUNT);
        } else {
            return new CommandResult(Pages.WELCOME_PAGE, true);
        }
    }
}
