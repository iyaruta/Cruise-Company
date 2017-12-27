package com.epam.lab.cruisecompany.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConnectionPool {

    private static final String JDBC_CONFIG = "/jdbc.properties";
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static HikariDataSource dataSource;

    static {
        HikariConfig cfg = new HikariConfig(JDBC_CONFIG);
        dataSource = new HikariDataSource(cfg);
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Get connection failed", e);
            return null;
        }
    }

    public static <T> List<T> findAll(String sql, Function<ResultSet, T> function) {
        List<T> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                T type = function.apply(resultSet);
                result.add(type);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
        }

        return result;
    }


}
