package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.ExcursionToUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ExcursionToUserDaoImpl implements ExcursionToUserDao {

    private static final Logger LOG = LoggerFactory.getLogger(ExcursionToUserDaoImpl.class);

    @Override
    public void buy(Long excursionId, Long userId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO EXCURSION_TO_USER(excursion_id, user_id) VALUES (?, ?)")) {
            statement.setLong(1, excursionId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }
}
