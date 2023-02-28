package com.epam.Per1.command;

import com.epam.Per1.command.impl.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The ActionFactory class is responsible for creating an instance of an ActionCommand object based on the
 * HttpServletRequest object provided to the defineCommand method.
 * It contains a single method, defineCommand, which takes an HttpServletRequest object as input and returns an
 * instance of an ActionCommand object.
 *
 * @author Alexander Bukhalenkov
 */
public class ActionFactory {

    private static Logger log = LogManager.getLogger(ActionFactory.class);

    /**
     * Creates an instance of an ActionCommand object based on the HttpServletRequest object provided.
     * Extracts the action to be performed from the request URI and content type, and uses the CommandType class
     * to obtain the corresponding ActionCommand object.
     * If the action is not recognized, sets the "wrongAction" attribute of the request to true and logs an error message.
     *
     * @param request the HttpServletRequest object representing the request being made
     * @return an instance of an ActionCommand object that can handle the request
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand currentCommand = new EmptyCommand();
        String action = request.getRequestURI()
                .replace("/", "")
                .replace("Per1", "");
        action += request.getContentType() == null ? "get" : "post";
        log.info("Found command: " + action);
        if (action != null && !action.isEmpty()) {
            try {
                currentCommand = CommandType.getCurrentCommand(action);
            } catch (IllegalArgumentException e) {
                request.setAttribute("wrongAction", true);
                log.info("Wrong action!: " + action);
            }
        }
        return currentCommand;
    }

}
