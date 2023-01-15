package com.epam.Per1.listener;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class CustomServletRequestListener implements ServletRequestListener {

    private static Logger log = LogManager.getLogger(CustomServletRequestListener.class);

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        log.info("ServletRequest destroyed. Remote IP="+servletRequest.getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        log.info("ServletRequest initialized. Remote IP="+servletRequest.getRemoteAddr());
        log.debug("Start servlet " + ((HttpServletRequest)servletRequest).getRequestURL().toString());
    }

}
