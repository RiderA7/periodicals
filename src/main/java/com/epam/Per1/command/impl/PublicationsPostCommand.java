package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.exception.DbException;
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

/**
 * Publications list POST-method controller command.
 *
 * @author Alexander Bukhalenkov
 */
public class PublicationsPostCommand implements ActionCommand {
    private final TopicService topicService = new TopicService(DaoFactory.getInstance().getTopicDao());
    private final PublicationService publicationService = new PublicationService(DaoFactory.getInstance().getPublicationDao());
    private static Logger log = LogManager.getLogger(PublicationsPostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DbException {
        log.debug("Found session publication:" + req.getSession().getAttribute("publication"));
        if(isActionAddPublication(req) || isActionEditPublication(req)){
            log.debug("edit or add");
            LoadListOfTopicsToSession(req);
            Publication publication = new Publication();
            String action = "add";
            if(isActionEditPublication(req)) {
                log.debug("edit");
                publication = getPublicationByIdFromRequest(req);
                if (publication != null) {
                    log.debug("edit approve");
                    action = "edit";
                }
            }
            req.getSession().setAttribute("publication", publication);
            req.getSession().setAttribute("action", action);
            return new CommandResult(Pages.ADMIN_PUBLICATIONS,true);
        }

        if(isActionDeletePublication(req)){
            Publication publication = publicationService.getById(Integer.parseInt(req.getParameter("pubId")));
            String message = "general.publication.delete.";
            if(publicationService.delete(publication)){
                message += "success";
                req.getSession().setAttribute("suc",message);
            } else {
                message += "fail";
                req.getSession().setAttribute("err",message);
            }
            return new CommandResult(Commands.PUBLICATIONS, true);
        }

        if(req.getParameter("removeTopic") != null){
            log.info("remove session activeTopic");
            req.getSession().removeAttribute("activeTopic");
            return new CommandResult(Commands.TOPICS, true);
        }

        if(req.getParameter("view") != null && req.getParameter("pubId") != null){
            log.debug("view publication");
            int pubId = Integer.parseInt(req.getParameter("pubId"));
log.error("NOT IMPLEMENTED YET");
        }

        try {
            if (req.getParameter("action") != null) {
                log.debug("action");
                String action = req.getParameter("action");
                Publication publication = getPublicationFromRequest(req);
                String success = "admin.publication.";
                boolean done = false;
                switch (action) {
                    case "add":
                        log.debug("adding");
                        done = createPublication(publication);
                        success += "added";
                        break;
                    case "edit":
                        log.debug("editing");
                        if (publication.getId() != 0) done = updatePublication(publication);
                        success += "updated";
                        break;
                }
                req.getSession().setAttribute("suc", success);
            }
        } catch (DbException e) {
            log.debug("error action");
            req.getSession().setAttribute("err", e.getMessage());
        }
        return new CommandResult(Commands.PUBLICATIONS, true);
    }

    private Publication getPublicationByIdFromRequest(HttpServletRequest req) {
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

    private boolean isActionDeletePublication(HttpServletRequest req) {
        return req.getParameter("delete") != null && req.getParameter("pubId") != null;
    }

    private boolean createPublication(Publication publication){
        return publicationService.create(publication);
    }

    private boolean updatePublication(Publication publication){
        return publicationService.update(publication);
    }

    private Publication getPublicationFromRequest(HttpServletRequest req) throws DbException {
        Topic topic = topicService.getById(Integer.parseInt(req.getParameter("topicId")));
        double price;
        try {
            price = Double.parseDouble(req.getParameter("publicationPrice"));
        } catch (NumberFormatException e) {
            String error = "invalid.price";
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
