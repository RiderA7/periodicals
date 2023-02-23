package com.epam.Per1.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Custom checked exception type which is to be throws in DAO layer.
 *
 * @author Alexander Bukhalenkov
 */
public class DbException extends AppException {

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
