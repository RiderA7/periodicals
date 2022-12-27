package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.User;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AccountChangePassGetCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            req.setAttribute("user", user);
            return new CommandResult(Pages.USER_CHANGE_PASSWORD);
        } catch (NullPointerException e) {
            return new CommandResult(Pages.WELCOME_PAGE);
        }
    }
}
