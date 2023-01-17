package com.epam.Per1.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionListener implements HttpSessionListener {

    private static Logger log = LogManager.getLogger(SessionListener.class);

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        log.info("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        log.info("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }

}
