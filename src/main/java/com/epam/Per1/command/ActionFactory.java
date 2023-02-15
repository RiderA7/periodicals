package com.epam.Per1.command;

import com.epam.Per1.command.impl.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ActionFactory class. Contains all available actions and method to get any of them.
 *
 * @author Alexander Bukhalenkov
 */
public class ActionFactory {

    private static Logger log = LogManager.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand currentCommand = new EmptyCommand();
        String action = request.getRequestURI()
                .replace("/", "")
                .replace("Per1", "");
        action += request.getContentType() == null ? "get" : "post";
        log.info("Found command: "+action);
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
