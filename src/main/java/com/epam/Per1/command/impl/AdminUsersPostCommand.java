package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Commands;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AdminUsersPostCommand implements ActionCommand {
    private final UserService userService = new UserService();
    private static Logger log = LogManager.getLogger(AdminUsersPostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action") != null) {
            String action = req.getParameter("action");
            log.info("Found action="+action);
            if (action.equals("user")) {
                if (req.getParameter("userId") != null) {
                    int userId = Integer.parseInt(req.getParameter("userId"));
                    String ban = req.getParameter("ban");
                    log.info(ban + " UserId=" + userId);
                    userService.ban(userId, ban.equals("BAN"));
                }
            }
        }
        return new CommandResult(Commands.ADMIN_USERS, true);
    }
}
