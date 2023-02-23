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

/**
 * Publications list GET-method controller command.
 *
 * @author Alexander Bukhalenkov
 */
public class PublicationsGetCommand implements ActionCommand {
    private final TopicService topicService = new TopicService(DaoFactory.getInstance().getTopicDao());
    private final PublicationService publicationService = new PublicationService(DaoFactory.getInstance().getPublicationDao());
    private static Logger log = LogManager.getLogger(PublicationsGetCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Topic activeTopic = LoadActiveTopicFromSession(session);
//        PagingParams pagingParams = PreparePagingParams(req, session, activeTopic);
//        SqlParams sqlParams = PrepareSqlParams(req, activeTopic, pagingParams);
        SqlParams sqlParams = new SqlParams();
        PagingParams pagingParams = new PagingParams();
        HttpSession session = req.getSession();
        if (session.getAttribute("paging_publications") != null) {
            pagingParams = (PagingParams) session.getAttribute("paging_publications");
            log.debug("PagingParams after loading from session:" + pagingParams);
        }
        if(req.getParameter("reset") != null){
            log.debug("Resetting paging");
            pagingParams.setFilter("");
            pagingParams.setOrderBy("");
            pagingParams.setSort("");
            pagingParams.setPage(0);
        }
        Topic activeTopic = loadActiveTopicFromSession(session);
        if (activeTopic.getId() != 0) {
            log.debug("Set activeTopic");
            sqlParams.addWhere("publication_topic=" + activeTopic.getId());
        }
        if(!getFilterFromRequest(req).equals("")){
            log.debug("Set filter");
            pagingParams.setFilter(getFilterFromRequest(req));
        }
        if(!getOrderFromRequest(req).equals("")) {
            log.debug("Set order");
            pagingParams.setOrderBy(getOrderFromRequest(req));
        }
        if(!getSortFromRequest(req).equals("")) {
            pagingParams.setSort(getSortFromRequest(req));
        }
        if (!pagingParams.getFilter().equals("")) {
            sqlParams.addWhere("publication_title LIKE '%" + pagingParams.getFilter() + "%'");
            log.debug("where = " + sqlParams.getWhereList());
        }
        int totalPublications = publicationService.countAll(sqlParams);
        log.debug("Total pubs:" + totalPublications);
        pagingParams.setTotal(totalPublications);
        if (req.getParameter("page") != null) {
            pagingParams.setPage(Integer.parseInt(req.getParameter("page")) - 1);
        }
        session.setAttribute("paging_publications", pagingParams);
        sqlParams.setOffsetAndLimit(pagingParams);
        if(!pagingParams.getOrderBy().equals("")){
            String sort = "ASC";
            if(pagingParams.getSort().equals("DESC")){
                sort = pagingParams.getSort();
            }
            if(pagingParams.getOrderBy().equals("title")) {
                sqlParams.setSort("ORDER BY publication_title " + sort);
            }
            if(pagingParams.getOrderBy().equals("price")) {
                sqlParams.setSort("ORDER BY publication_price " + sort);
            }
        }
        log.debug(sqlParams);
        log.debug(pagingParams);
        loadPublicationListToRequest(req, sqlParams);
        StringBuilder pageParamsGet = preparePageParamsGet(pagingParams);
        log.debug("pageParamsGet = " + pageParamsGet);
        return new CommandResult(pageParamsGet.toString());
    }

    private String getSortFromRequest(HttpServletRequest req) {
        if (req.getParameter("sort") != null) {
            return req.getParameter("sort");
        }
        return "ASC";
    }

    private String getOrderFromRequest(HttpServletRequest req) {
        if (req.getParameter("order") != null) {
            return req.getParameter("order");
        }
        return "";
    }

    private StringBuilder preparePageParamsGet(PagingParams pagingParams) {
        StringBuilder pageParamsGet;
        pageParamsGet = new StringBuilder(Pages.PUBLICATIONS)
                .append("?").append("currentPage=").append(pagingParams.getCurrentPage())
                .append("&").append("maxPageNum=").append(pagingParams.getMaxPageNum())
                .append("&").append("filter=").append(pagingParams.getFilter())
                .append("&").append("sort=").append(pagingParams.getOrderBy());
        return pageParamsGet;
    }

    private String getFilterFromRequest(HttpServletRequest req) {
        if (req.getParameter("filter") != null) {
            return req.getParameter("filter");
        }
        return "";
    }

//    private void prepareParams(HttpServletRequest req, PagingParams pagingParams, SqlParams sqlParams) {
//        HttpSession session = req.getSession();
//        Topic activeTopic = loadActiveTopicFromSession(session);
//        if (activeTopic.getId() != 0) {
//            sqlParams.addWhere("topic_id=" + activeTopic.getId());
//        }
//        if (req.getParameter("filter") != null) {
//            sqlParams.addWhere("publication_title LIKE '%" + getFilterFromRequest(req) + "%'");
//        }
//        log.debug("where = " + sqlParams.getWhereList());
//        int totalPublications = publicationService.countAll(sqlParams);
//        log.debug("Total pubs:" + totalPublications);
//        if (session.getAttribute("paging_publications") != null) {
//            pagingParams = (PagingParams) session.getAttribute("paging_publications");
//            pagingParams.setTotal(totalPublications);
//            log.debug("PagingParams after loading from session:" + pagingParams);
//        } else {
//            pagingParams = new PagingParams(totalPublications, 2);
//            log.debug("PagingParams after creation:" + pagingParams);
//        }
//        if (req.getParameter("page") != null) {
//            pagingParams.setPage(Integer.parseInt(req.getParameter("page")) - 1);
//        }
//        session.setAttribute("paging_publications", pagingParams);
//        sqlParams.setOffsetAndLimit(pagingParams);
//        System.out.println("Before exit: " + pagingParams);
//    }

//    private PagingParams PreparePagingParams(HttpServletRequest req, HttpSession session, Topic activeTopic) {
//        int totalPublications = publicationService.countAll(activeTopic.getId());
//        log.debug("Total pubs:" + totalPublications);
//        PagingParams pagingParams;
//        if (session.getAttribute("paging_publications") != null) {
//            pagingParams = (PagingParams) session.getAttribute("paging_publications");
//            pagingParams.setTotal(totalPublications);
//        } else {
//            pagingParams = new PagingParams(totalPublications, 2);
//        }
//        if (req.getParameter("page") != null) {
//            pagingParams.setPage(Integer.parseInt(req.getParameter("page")) - 1);
//        }
//        session.setAttribute("paging_publications", pagingParams);
//        return pagingParams;
//    }

    private void loadPublicationListToRequest(HttpServletRequest req, SqlParams sqlParams) {
        List<Publication> publications = publicationService.getLimit(sqlParams);
        req.setAttribute("publications", publications);
    }

//    private SqlParams PrepareSqlParams(HttpServletRequest req, Topic activeTopic, PagingParams pagingParams) {
//        SqlParams sqlParams = new SqlParams.Builder()
//                .setOffsetAndLimit(pagingParams)
//                .getSqlParams();
//        if (activeTopic.getId() != 0) {
//            sqlParams.addWhere("topic_id=" + activeTopic.getId());
//        }
//        if (req.getParameter("filter") != null) {
//            sqlParams.addWhere("publication_title LIKE '%" + req.getParameter("filter") + "%'");
//        }
//        log.debug("where = " + sqlParams.getWhereList());
//        return sqlParams;
//    }

    private Topic loadActiveTopicFromSession(HttpSession session) {
        Topic activeTopic = new Topic();
        if (session.getAttribute("activeTopic") != null) {
            activeTopic = (Topic) session.getAttribute("activeTopic");
        }
        return activeTopic;
    }
}
