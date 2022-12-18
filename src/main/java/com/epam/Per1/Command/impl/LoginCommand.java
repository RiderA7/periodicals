package com.epam.Per1.Command.impl;

import com.epam.Per1.Command.ActionCommand;
import com.epam.Per1.Command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
