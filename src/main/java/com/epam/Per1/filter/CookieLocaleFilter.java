package com.epam.Per1.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "CookieLocaleFilter", urlPatterns = {"/*"})
public class CookieLocaleFilter implements Filter {

    private static Logger log = LogManager.getLogger(CookieLocaleFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getParameter("locale") != null) {
            String locale = req.getParameter("locale");
            Cookie cookie = new Cookie("lang", locale);
            res.addCookie(cookie);
            log.info("Set lang to " + locale);
            res.sendRedirect(req.getHeader("referer"));
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}