package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.UserDao;
import home.inna.cruisecompany.data.Role;
import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.servlet.IndexServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class);
    private static final String SQL = "INSERT INTO USER (name, role, password) VALUES (?, ?, ?)";

    @Override
    public User getByName(String name) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statementName(connection, name);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return user;
    }

    @Override
    public User get(Long id) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statement(connection, id);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return user;
    }

    @Override
    public void save(User user) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, user.getName());
            statement.setLong(2, user.getRole().ordinal());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    private PreparedStatement statement(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER WHERE id = ?");
        statement.setLong(1, id);
        return statement;
    }

    private PreparedStatement statementName(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER WHERE name = ?");
        statement.setString(1, name);
        return statement;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        int roleNum = resultSet.getInt("role");
        user.setRole(Role.values()[roleNum - 1]);
        return user;
    }
}
