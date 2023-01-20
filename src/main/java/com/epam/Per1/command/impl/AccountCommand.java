package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Commands;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AccountCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AccountCommand.class);
    private final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            return new CommandResult(Commands.SUBSCRIPTIONS);
    }
}
