package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.Publication;
import com.epam.Per1.entity.Topic;
import com.epam.Per1.service.impl.PublicationService;
import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.utils.Pages;
import com.epam.Per1.utils.PagingParams;
import com.epam.Per1.utils.SqlParams;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class PublicationsGetCommand implements ActionCommand {
    private final TopicService topicService = new TopicService(DaoFactory.getInstance().getTopicDao());
    private final PublicationService publicationService = new PublicationService(DaoFactory.getInstance().getPublicationDao());
    private static Logger log = LogManager.getLogger(PublicationsGetCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Topic activeTopic = new Topic();
        if(session.getAttribute("activeTopic") != null){
            activeTopic = (Topic) session.getAttribute("activeTopic");
        }
        List<Publication> publications;
        int totalPublications = publicationService.countAll(activeTopic.getId());
        log.debug("Total pubs:" + totalPublications);
        PagingParams pagingParams;
        if(session.getAttribute("paging_publications") != null){
            pagingParams = (PagingParams) session.getAttribute("paging_publications");
            pagingParams.setTotal(totalPublications);
        } else {
            pagingParams = new PagingParams(totalPublications, 2);
        }
        int pageReq = 0;
        if(req.getParameter("page") != null){
            pageReq = Integer.parseInt(req.getParameter("page"));
            pagingParams.setPage(pageReq-1);
        }
        session.setAttribute("paging_publications", pagingParams);
        String where = (activeTopic.getId() == 0) ? "" : "topic_id="+activeTopic.getId();
        SqlParams sqlParams = new SqlParams.Builder()
                .setWhere(where)
                .setOffsetAndLimit(pagingParams)
                .getSqlParams();
        publications = publicationService.getLimit(sqlParams);
        req.setAttribute("publications", publications);
        StringBuilder pageParamsGet;
        pageParamsGet = new StringBuilder(Pages.PUBLICATIONS)
                .append("?").append("currentPage=").append(pagingParams.getCurrentPage())
                .append("&").append("maxPageNum=").append(pagingParams.getMaxPageNum());
        return new CommandResult(pageParamsGet.toString());
    }
}
