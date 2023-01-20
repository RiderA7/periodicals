package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dto.SubscriptionDTO;
import com.epam.Per1.dto.UserDTO;
import com.epam.Per1.service.impl.PublicationService;
import com.epam.Per1.service.impl.SubscriptionService;
import com.epam.Per1.service.impl.TopicService;
import com.epam.Per1.service.impl.UserService;
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

public class AccountSubscribesGetCommand implements ActionCommand {
    private final TopicService topicService = new TopicService(DaoFactory.getInstance().getTopicDao());
    private final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());
    private final PublicationService publicationService = new PublicationService(DaoFactory.getInstance().getPublicationDao());
    private final SubscriptionService subscriptionService = new SubscriptionService(DaoFactory.getInstance().getSubscriptionDao());
    private static Logger log = LogManager.getLogger(AccountSubscribesGetCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO user = LoadUserFromSession(session);
        PagingParams pagingParams = PreparePagingParams(req, session, user);
        SqlParams sqlParams = PrepareSqlParams(user, pagingParams);
        LoadPublicationListToRequest(req, sqlParams);
        StringBuilder pageParamsGet = PreparePageParamsGet(pagingParams);
        return new CommandResult(pageParamsGet.toString());
    }

    private StringBuilder PreparePageParamsGet(PagingParams pagingParams) {
        StringBuilder pageParamsGet;
        pageParamsGet = new StringBuilder(Pages.SUBSCRIPTIONS)
                .append("?").append("currentPage=").append(pagingParams.getCurrentPage())
                .append("&").append("maxPageNum=").append(pagingParams.getMaxPageNum());
        return pageParamsGet;
    }

    private PagingParams PreparePagingParams(HttpServletRequest req, HttpSession session, UserDTO user) {
        int totalSubscriptions = subscriptionService.countAll(user.getId());
        log.debug("Total pubs:" + totalSubscriptions);
        PagingParams pagingParams;
        if(session.getAttribute("paging_subscriptions") != null){
            pagingParams = (PagingParams) session.getAttribute("paging_subscriptions");
            pagingParams.setTotal(totalSubscriptions);
        } else {
            pagingParams = new PagingParams(totalSubscriptions, 2);
        }
        if(req.getParameter("page") != null){
            pagingParams.setPage(Integer.parseInt(req.getParameter("page"))-1);
        }
        session.setAttribute("paging_subscriptions", pagingParams);
        return pagingParams;
    }

    private void LoadPublicationListToRequest(HttpServletRequest req, SqlParams sqlParams) {
        List<SubscriptionDTO> subscriptions = subscriptionService.getLimit(sqlParams);
        log.debug("Subs list:"+subscriptions);
        req.setAttribute("subscriptions", subscriptions);
    }

    private SqlParams PrepareSqlParams(UserDTO user, PagingParams pagingParams) {
        String where = (user.getId() == 0) ? "" : "user="+ user.getId();
        return new SqlParams.Builder()
                .setWhere(where)
                .setOffsetAndLimit(pagingParams)
                .getSqlParams();
    }

    private UserDTO LoadUserFromSession(HttpSession session) {
        UserDTO user = null;
        if(session.getAttribute("userDTO") != null){
            user = (UserDTO) session.getAttribute("userDTO");
        }
        return user;
    }
}
