package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.utils.Commands;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class TopicsPostCommand implements ActionCommand {
    private final TopicService topicService = new TopicService(DaoFactory.getInstance().getTopicDao());
    private static Logger log = LogManager.getLogger(TopicsPostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Found session topic:" + req.getSession().getAttribute("topic"));
        if(isActionAddTopic(req) || isActionEditTopic(req)){
            Topic topic = new Topic();
            String action = "add";
            if(isActionEditTopic(req)) {
                Optional<Topic> optionalTopic = getTopicByIdFromRequest(req);
                if (optionalTopic.isPresent()) {
                    topic = optionalTopic.get();
                    action = "edit";
                }
            }
            req.getSession().setAttribute("topic", topic);
            req.getSession().setAttribute("action", action);
            return new CommandResult(Pages.ADMIN_TOPICS,true);
        }

        if(isOpenTopic(req)){
            int topicId = Integer.parseInt(req.getParameter("topicId"));
            Optional<Topic> optionalTopic = topicService.getById(topicId);
            req.getSession().setAttribute("activeTopic", optionalTopic.orElse(null));
            return new CommandResult(Commands.PUBLICATIONS);
        }

            if (req.getParameter("action") != null) {
                log.debug("action");
                String action = req.getParameter("action");
                Topic topic = getTopicFromRequest(req);
                String success = "admin.topic.";
                boolean done = false;
                switch (action) {
                    case "add":
                        log.debug("adding");
                        done = createTopic(topic);
                        success += "added";
                        break;
                    case "edit":
                        log.debug("editing");
                        if (topic.getId() != 0) done = updateTopic(topic);
                        success += "updated";
                        break;
                }
                req.getSession().setAttribute("suc", success);
            }
        return new CommandResult(Commands.TOPICS, true);
    }

    private boolean isOpenTopic(HttpServletRequest req) {
        return req.getParameter("open") != null && req.getParameter("topicId") != null;
    }

    private boolean createTopic(Topic topic){
        return topicService.create(topic);
    }

    private boolean updateTopic(Topic topic){
        return topicService.update(topic);
    }

    private boolean isActionAddTopic(HttpServletRequest req) {
        return req.getParameter("add") != null;
    }

    private boolean isActionEditTopic(HttpServletRequest req) {
        return req.getParameter("edit") != null && req.getParameter("topicId") != null;
    }
    private Optional<Topic> getTopicByIdFromRequest(HttpServletRequest req) {
        int topicId = Integer.parseInt(req.getParameter("topicId"));
        return topicService.getById(topicId);
    }
    private Topic getTopicFromRequest(HttpServletRequest req) {
        return new Topic.Builder()
                .setId(Integer.parseInt(req.getParameter("id")))
                .setName(req.getParameter("topicName"))
                .getTopic();
    }

}
