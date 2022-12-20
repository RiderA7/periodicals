package com.epam.Per1.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ActionCommand {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
