package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.service.impl.PublicationService;
import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.PagingParams;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class PublicationsGetCommand implements ActionCommand {
    private final TopicService topicService = new TopicService();
    private final PublicationService publicationService = new PublicationService();
    private static Logger log = LogManager.getLogger(PublicationsGetCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Topic> topics;
        int totalTopics = topicService.countAll();
        HttpSession session = req.getSession();
        PagingParams pagingParams;
        if(session.getAttribute("paging_topics") != null){
            pagingParams = (PagingParams) session.getAttribute("paging_topics");
            pagingParams.setTotal(totalTopics);
        } else {
            pagingParams = new PagingParams(totalTopics, 2);
        }
        int pageReq = 0;
        if(req.getParameter("page") != null){
            pageReq = Integer.parseInt(req.getParameter("page"));
            pagingParams.setPage(pageReq-1);
        }
        session.setAttribute("paging_topics", pagingParams);
        topics = topicService.getLimit("","","",pagingParams);
        StringBuilder pageParamsGet = new StringBuilder(Pages.TOPICS);
        pageParamsGet.append("?").append("currentPage=").append(pagingParams.getCurrentPage())
                .append("&").append("maxPageNum=").append(pagingParams.getMaxPageNum());
        req.setAttribute("topics", topics);
        return new CommandResult(pageParamsGet.toString());
    }
}
