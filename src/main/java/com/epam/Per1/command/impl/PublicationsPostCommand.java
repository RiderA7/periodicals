package com.epam.Per1.command.impl;

import com.epam.Per1.DbException;
import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.service.impl.PublicationService;
import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.utils.Commands;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PublicationsPostCommand implements ActionCommand {
    private final TopicService topicService = new TopicService();
    private final PublicationService publicationService = new PublicationService();
    private static Logger log = LogManager.getLogger(PublicationsPostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DbException {
        log.debug("Found session publication:" + req.getSession().getAttribute("publication"));
        if(isActionAddPublication(req) || isActionEditPublication(req)){
            LoadListOfTopicsToSession(req);
            Publication publication = new Publication();
            String action = "add";
            if(isActionEditPublication(req)) {
                Optional<Publication> optionalPublication = getPublicationByIdFromRequest(req);
                if (optionalPublication.isPresent()) {
                    publication = optionalPublication.get();
                    action = "edit";
                }
            }
            req.getSession().setAttribute("publication", publication);
            req.getSession().setAttribute("action", action);
            return new CommandResult(Pages.ADMIN_PUBLICATIONS,true);
        }

        if(req.getParameter("removeTopic") != null){
            log.info("remove session activeTopic");
            req.getSession().removeAttribute("activeTopic");
            return new CommandResult(Commands.TOPICS, true);
        }

        if(req.getParameter("view") != null && req.getParameter("pubId") != null){
            int pubId = Integer.parseInt(req.getParameter("pubId"));
log.error("NOT IMPLEMENTED YET");
        }

        try {
            if (req.getParameter("action") != null) {
                String action = req.getParameter("action");
                Publication publication = getPublicationFromRequest(req);
                String success = "Publication ";
                boolean done = false;
                switch (action) {
                    case "add":
                        done = createPublication(publication);
                        success += "added";
                        break;
                    case "edit":
                        if (publication.getId() != 0) done = updatePublication(publication);
                        success += "updated";
                        break;
                }
                req.getSession().setAttribute("suc", success);
            }
        } catch (DbException e) {
            req.getSession().setAttribute("err", e.getMessage());
        }
        return new CommandResult(Commands.PUBLICATIONS, true);
    }

    private Optional<Publication> getPublicationByIdFromRequest(HttpServletRequest req) {
        int pubId = Integer.parseInt(req.getParameter("pubId"));
        return publicationService.getById(pubId);
    }

    private void LoadListOfTopicsToSession(HttpServletRequest req) {
        List<Topic> topics = topicService.getAll();
        req.getSession().setAttribute("topics", topics);
    }

    private boolean isActionAddPublication(HttpServletRequest req) {
        return req.getParameter("add") != null;
    }

    private boolean isActionEditPublication(HttpServletRequest req) {
        return req.getParameter("edit") != null && req.getParameter("pubId") != null;
    }

    private boolean createPublication(Publication publication){
        return publicationService.create(publication);
    }

    private boolean updatePublication(Publication publication){
        return publicationService.update(publication);
    }

    private Publication getPublicationFromRequest(HttpServletRequest req) throws DbException {
        Optional<Topic> optionalTopic =
                topicService.getById(Integer.parseInt(req.getParameter("topicId")));
        Topic topic = optionalTopic.orElseThrow();
        double price;
        try {
            price = Double.parseDouble(req.getParameter("publicationPrice"));
        } catch (NumberFormatException e) {
            String error = "Invalid price: " + e.getMessage();
            log.error(error);
            throw new DbException(error);
        }
        return new Publication.Builder()
                .setId(Integer.parseInt(req.getParameter("id")))
                .setName(req.getParameter("publicationName"))
                .setPrice(price)
                .setTopic(topic)
                .getPublication();
    }
}
