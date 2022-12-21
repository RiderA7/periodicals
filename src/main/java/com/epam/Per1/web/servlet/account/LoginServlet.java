package com.epam.Per1.web.servlet.account;

import com.epam.Per1.AppException;
import com.epam.Per1.DbException;
import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.entity.User;
import com.epam.Per1.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/account1/login")
public class LoginServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        char[] password = req.getParameter("password").toCharArray();

        if (login.isEmpty() || password.length == 0) {
            log.info("Login not success");
            req.setAttribute("err", "You must enter login and password");
//            resp.sendRedirect(req.getContextPath() + "/account/login");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        try {
            User user = DaoFactory.getInstance().getUserDao().login(login, password);
            if(user == null) {
                log.info("User "+login+" not found");
                req.setAttribute("err", "Wrong login or password!");
//                resp.sendRedirect(req.getContextPath() + "/account/login");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            } else {
                log.info("User "+login+" logged in");
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/account");
            }
        } catch (DbException e) {
            log.error(Utils.getErrMessage(e));
            throw new AppException(e);
        }
    }
}
