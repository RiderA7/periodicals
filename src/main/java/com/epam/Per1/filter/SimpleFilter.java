package com.epam.Per1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter({"/*"})
public class SimpleFilter extends HttpFilter {

    private static Logger log = LogManager.getLogger(SimpleFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        log.info("SimpleFilter start");
        if(req.getAttribute("err") != null){
            log.info("not found err");
            req.setAttribute("err", "!!!!!!!" + req.getAttribute("err"));
        }
//        log.info("SimpleFilter end");
        chain.doFilter(req, res);
    }
}
