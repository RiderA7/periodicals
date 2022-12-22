package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.User;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LogoutCommand implements ActionCommand {

    private static Logger log = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final HttpSession session = req.getSession();
        Long userId = ((User) session.getAttribute("user")).getId();
        session.removeAttribute("user");
        session.removeAttribute("role");
        log.info("user with id = " + userId + " logged out");
        return new CommandResult(Pages.WELCOME_PAGE, true);
    }
}
