package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Admin page GET-method controller command.
 *
 * @author Alexander Bukhalenkov
 */
public class AdminCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AdminCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            return new CommandResult(Pages.ADMIN_ACCOUNT);
    }
}
