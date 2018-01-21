package home.inna.cruisecompany.dao;

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

    public static Connection getConnection(boolean autoCommit) {
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(autoCommit);
            if (testMode) {
                connection.setAutoCommit(false);
            }
            return connection;
        } catch (SQLException e) {
            LOG.error("Get connection failed", e);
            return null;
        }
    }

    public static Connection getConnection() {
        return getConnection(true);
    }

    public static boolean isTestMode() {
        return testMode;
    }

    static void setTestMode(boolean testMode) {
        ConnectionPool.testMode = testMode;
    }
}
