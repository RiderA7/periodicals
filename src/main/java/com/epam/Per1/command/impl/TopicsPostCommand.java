package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
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
    private final TopicService topicService = new TopicService();
    private static Logger log = LogManager.getLogger(TopicsPostCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Found session topic:" + req.getSession().getAttribute("topic"));
        if(req.getParameter("edit") != null && req.getParameter("topicId") != null){
            int topicId = Integer.parseInt(req.getParameter("topicId"));
            Optional<Topic> optionalTopic = topicService.getById(topicId);
            if(optionalTopic.isPresent()){
                Topic topic = optionalTopic.get();
                req.getSession().setAttribute("topic", topic);
                log.debug("EDIT " + topicId + " " + topic);
                return new CommandResult(Pages.ADMIN_TOPICS, true);
            }
        }

        if(req.getParameter("add") != null){
            if(req.getSession().getAttribute("topic") != null){
                req.getSession().removeAttribute("topic");
            }
            log.debug("ADD new topic");
            return new CommandResult(Pages.ADMIN_TOPICS,true);
        }

        if(req.getParameter("open") != null && req.getParameter("topicId") != null){
            int topicId = Integer.parseInt(req.getParameter("topicId"));
            Optional<Topic> optionalTopic = topicService.getById(topicId);
            req.getSession().setAttribute("activeTopic", optionalTopic.orElse(null));
            return new CommandResult(Commands.PUBLICATIONS);
        }

        if(req.getParameter("action") != null){
            String action = req.getParameter("action");
            String topicName = (req.getParameter("topicName") != null)?
                    req.getParameter("topicName") : "New Topic";
            int topicId =  (req.getParameter("id") != null)?
                    Integer.parseInt(req.getParameter("id")) : 0;
            Topic topic = new Topic.Builder().setName(topicName).setId(topicId).getTopic();
            boolean done = false;
            switch (action) {
                case "add": done = createTopic(topic); break;
                case "edit": if(topic.getId() != 0) done = updateTopic(topic);
                break;
            }
        }

        return new CommandResult(Commands.TOPICS, true);
    }

    private boolean createTopic(Topic topic){
        return topicService.create(topic);
    }

    private boolean updateTopic(Topic topic){
        return topicService.update(topic);
    }
}
