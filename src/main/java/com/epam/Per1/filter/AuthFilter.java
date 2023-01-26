package com.epam.Per1.filter;

import com.epam.Per1.dao.DaoFactory;
import com.epam.Per1.dto.UserDTO;
import com.epam.Per1.service.impl.UserService;
import com.epam.Per1.utils.Mapper;
import com.epam.Per1.utils.Pages;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;

@WebFilter({"/account/*", "/admin/*"})
public class AuthFilter extends HttpFilter {

    private UserService userService;
    private Logger log;

    public AuthFilter(){
        userService = new UserService(DaoFactory.getInstance().getUserDao());
        log = LogManager.getLogger(AuthFilter.class);
    }
    public AuthFilter(UserService userService, Logger log){
        this.userService = userService;
        this.log = log;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
//        log.debug("AuthFilter start");

        if(ifNotAuthorized(req)){
            req.getRequestDispatcher(Pages.LOGIN_PAGE).forward(req, res);
        } else {

            UserDTO user = (UserDTO) req.getSession().getAttribute("userDTO");
            user = refreshUserDataInSession(req, user);

            if(isAccessAllowedAccordingByRole(req, user)){
                req.getRequestDispatcher(Pages.LOGIN_PAGE).forward(req, res);
            } else {

//        log.debug("AuthFilter end");
                chain.doFilter(req, res);
            }
        }
    }

    private boolean isAccessAllowedAccordingByRole(HttpServletRequest req, UserDTO user) {
        String servletPath = req.getServletPath().split("/")[1];
        log.debug("Filter: servletPath = " + servletPath + " and role is " + user.getRole().getRole());
        if(servletPath.toUpperCase(Locale.ROOT).equals("ACCOUNT")
                && !user.getRole().getRole().toUpperCase(Locale.ROOT).equals("USER")
        || servletPath.toUpperCase(Locale.ROOT).equals("ADMIN")
                && !user.getRole().getRole().toUpperCase(Locale.ROOT).equals("ADMINISTRATOR")){
            String message = "authorization.required";
            log.info("Filter: " + message);
            req.getSession().setAttribute("err", message);
            return true;
        }
        return false;
    }

    private UserDTO refreshUserDataInSession(HttpServletRequest req, UserDTO user) {
        user = Mapper.toUserDTO(userService.getById(user.getId()));
        req.getSession().setAttribute("user", Mapper.toUser(user));
        req.getSession().setAttribute("userDTO", user);
        return user;
    }

    private boolean ifNotAuthorized(HttpServletRequest req){
        if(req.getSession().getAttribute("userDTO") == null){
            String message = "authorization.required";
            log.info("Filter: " + message);
            req.getSession().setAttribute("err", message);
            return true;
        }
        return false;
    }
}
