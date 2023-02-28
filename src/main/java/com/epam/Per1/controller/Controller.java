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

/**
 * The {@code Controller} class is a main HttpServlet.
 * Overrides doPost and doGet methods by calling
 * the own method processRequest(request, response).
 *
 * @author Alexander Bukhalenkov
 */
@WebServlet(name = "Controller", urlPatterns = {"/account/*", "/admin/*", "/topics", "/publications", "/login", "/register"})
public class Controller extends HttpServlet {

    /**
     * The logger object used to log messages related to this class.
     */
    private static Logger log = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    /**
     * Processes the request and generates a response.
     * Uses an ActionFactory object to obtain an ActionCommand object that can handle the request.
     * Calls the execute method of the ActionCommand object to obtain a CommandResult object.
     * If the CommandResult object contains a page, forwards the request to the specified page.
     * If the CommandResult object does not contain a page, redirects the request to the WELCOME_PAGE.
     *
     * @param req  the HttpServletRequest object representing the request being made
     * @param resp the HttpServletResponse object representing the response to be generated
     * @throws ServletException if there is a servlet-related problem
     * @throws IOException      if there is an I/O problem
     */
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
