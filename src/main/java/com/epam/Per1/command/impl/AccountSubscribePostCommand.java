package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.entity.User;
import com.epam.Per1.service.impl.PublicationService;
import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Commands;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AccountSubscribePostCommand implements ActionCommand {
    private final TopicService topicService = new TopicService(DaoFactory.getInstance().getTopicDao());
    private final PublicationService publicationService = new PublicationService(DaoFactory.getInstance().getPublicationDao());
    private final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());
    private static Logger log = LogManager.getLogger(AccountSubscribePostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        double userMoney = user.getMoney();
        String message;
        if(req.getParameter("pubId") == null) {
            message = "Publication not found";
            log.info(message);
            session.setAttribute("err", message);
            return new CommandResult(Commands.PUBLICATIONS,true);
        }
        Publication publication =
                publicationService.getById(Integer.parseInt(req.getParameter("pubId")));
        if(publication == null) {
            message = "Publication not found";
            log.info(message);
            session.setAttribute("err", message);
            return new CommandResult(Commands.PUBLICATIONS,true);
        }
//        Publication publication = optionalPublication.get();
        if(userMoney < publication.getPrice()){
            message = "You have not enough money";
            log.info(message);
            session.setAttribute("err", message);
            return new CommandResult(Commands.PUBLICATIONS,true);
        }


        return new CommandResult(Pages.SUBSCRIPTIONS);
    }
}
