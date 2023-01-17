package com.epam.Per1.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class MyDataSourceHikari {
    private static Logger log = LogManager.getLogger(MyDataSourceHikari.class);

    private static DataSource dataSource;

    private final static String URL_PROPERTY = "jdbcUrl";
    private final static String DRIVER = "dataSource.driver";
    private final static String DB_USER = "dataSource.username";
    private final static String DB_PASSWORD = "dataSource.password";

    public static synchronized DataSource getDataSource(String propertiesFile) {
        if (dataSource == null) {
            Properties properties = getProperties(propertiesFile);
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty(URL_PROPERTY));
            config.setUsername(properties.getProperty(DB_USER));
            config.setPassword(properties.getProperty(DB_PASSWORD));
            config.setDriverClassName(properties.getProperty(DRIVER));
            config.setDataSourceProperties(properties);
            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }

    private MyDataSourceHikari() {}

    private static Properties getProperties(String propertiesFile) {
        Properties properties = new Properties();
        try (InputStream resource = MyDataSourceHikari.class.getClassLoader().getResourceAsStream(propertiesFile)){
            properties.load(resource);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return properties;
    }


}
