package com.epam.Per1.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoSuchElementException extends AppException {

    private static Logger log = LogManager.getLogger(NoSuchElementException.class);

    public NoSuchElementException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    public NoSuchElementException(String message) {
        super(message);
        log.error(message);
    }
}
