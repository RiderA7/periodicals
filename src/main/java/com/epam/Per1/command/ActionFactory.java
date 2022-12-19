package com.epam.Per1.command;

import com.epam.Per1.command.impl.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionFactory {

    private static Logger log = LogManager.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand currentCommand = new EmptyCommand();
        String action = request.getRequestURI().replace("/", "");
        action += request.getContentType() == null ? "get" : "post";
        if (action == null || action.isEmpty()) {
            return currentCommand;
        }
        try {
            currentCommand = CommandType.getCurrentCommand(action);
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", true);
            log.info("Wrong action!");
        }
        return currentCommand;
    }

}
