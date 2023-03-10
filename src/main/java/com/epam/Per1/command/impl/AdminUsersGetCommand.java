package com.epam.Per1.command.impl;

import com.epam.Per1.command.ActionCommand;
import com.epam.Per1.command.CommandResult;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.User;
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

/**
 * Admin Users list GET-method controller command.
 *
 * @author Alexander Bukhalenkov
 */
public class AdminUsersGetCommand implements ActionCommand {
    private final UserService userService = new UserService(DaoFactory.getInstance().getUserDao());
    private static Logger log = LogManager.getLogger(AdminUsersGetCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users;
        int totalUsers = userService.countAll();
        HttpSession session = req.getSession();
        PagingParams pagingParams;
        if(session.getAttribute("paging_users") != null){
            pagingParams = (PagingParams) session.getAttribute("paging_users");
            pagingParams.setTotal(totalUsers);
        } else {
            pagingParams = new PagingParams(totalUsers, 2);
        }
        int pageReq = 0;
        if(req.getParameter("page") != null){
            pageReq = Integer.parseInt(req.getParameter("page"));
            pagingParams.setPage(pageReq-1);
        }
        session.setAttribute("paging_users", pagingParams);
        SqlParams sqlParams = new SqlParams(pagingParams);
        users = userService.getLimit(sqlParams);
        req.setAttribute("users", users);
        String pageParamsGet = Pages.ADMIN_USERS +
                "?" + "currentPage=" + pagingParams.getCurrentPage() +
                "&" + "maxPageNum=" + pagingParams.getMaxPageNum();
        return new CommandResult(pageParamsGet);
    }
}
