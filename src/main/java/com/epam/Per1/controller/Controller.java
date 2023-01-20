package com.epam.Per1.controller;

import com.epam.Per1.exception.DbException;
import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.ActionFactory;
import com.epam.Per1.command.CommandResult;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.epam.Per1.utils.Pages.WELCOME_PAGE;

@WebServlet(name = "Controller", urlPatterns = {"/account/*", "/admin/*", "/topics", "/publications", "/login", "/register"})
public class Controller extends HttpServlet {

    private static Logger log = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.info("enter controller");
        ActionFactory actionFactory = new ActionFactory();
        ActionCommand command = actionFactory.defineCommand(req);
        CommandResult commandResult = null;
        try {
            commandResult = command.execute(req, resp);
        } catch (DbException e) {
            log.error(e.getMessage());
        }
        String page;
        if (commandResult.getPage() != null) {
            page = commandResult.getPage();
            if (commandResult.isRedirect()) {
                resp.sendRedirect(req.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            }
        } else {
            page = WELCOME_PAGE;
            resp.sendRedirect(req.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
