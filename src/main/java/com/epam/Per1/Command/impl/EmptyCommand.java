package com.epam.Per1.Command.impl;

import com.epam.Per1.Command.ActionCommand;
import com.epam.Per1.Command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.Per1.utils.Pages.WELCOME_PAGE;

public class EmptyCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new CommandResult(WELCOME_PAGE, true);
    }
}
