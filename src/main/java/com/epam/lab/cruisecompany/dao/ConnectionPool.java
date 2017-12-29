package com.epam.lab.cruisecompany.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final String JDBC_CONFIG = "/jdbc.properties";
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static HikariDataSource dataSource;
    private static boolean testMode;

    static {
        HikariConfig cfg = new HikariConfig(JDBC_CONFIG);
        dataSource = new HikariDataSource(cfg);
    }

    public static Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            if (testMode) {
                connection.setAutoCommit(false);
            }
            return connection;
        } catch (SQLException e) {
            LOG.error("Get connection failed", e);
            return null;
        }
    }

    static void setTestMode(boolean testMode) {
        ConnectionPool.testMode = testMode;
    }
}
