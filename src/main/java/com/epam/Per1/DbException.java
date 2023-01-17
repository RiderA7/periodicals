package com.epam.Per1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbException extends Exception {

    private static Logger log = LogManager.getLogger(DbException.class);

    public DbException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    public DbException(String message) {
        super(message);
        log.error(message);
    }
}
