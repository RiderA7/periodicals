package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginGetCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return new CommandResult("/jsp/login.jsp");
    }
}
