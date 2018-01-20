package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.CruiseDao;
import home.inna.cruisecompany.data.Cruise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseDaoImpl implements CruiseDao {

    private static final Logger LOG = LoggerFactory.getLogger(CruiseDaoImpl.class);
    private static final String FIND = "SELECT c.* FROM Ticket t \n" +
            "  INNER JOIN USER_TO_TICKET utt ON t.id = utt.ticket_id \n" +
            "  INNER JOIN Cruise c ON t.cruise_id = c.id \n" +
            "  WHERE utt.user_id = ?";

    @Override
    public List<Cruise> findAll() {
        List<Cruise> cruises = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CRUISE");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cruise cruise = getCruise(resultSet);
                cruises.add(cruise);
            }

        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return cruises;
    }

    public List<Cruise> cruiseByUser(Long userId) {
        List<Cruise> cruises = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statementByUser(connection, userId);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cruise cruise = getCruise(resultSet);
                cruises.add(cruise);
            }

        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return cruises;
    }

    @Override
    public Cruise get(Long cruiseId) {
        Cruise cruise = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statement(connection, cruiseId);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                cruise = getCruise(resultSet);
            }

        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return cruise;
    }

    @Override
    public Long save(Cruise cruise) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = preparedStatement(connection, cruise)) {
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void update(Cruise cruise) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE CRUISE SET ship_id = ?, name = ? WHERE id = ?")) {
            statement.setLong(1, cruise.getShipId());
            statement.setString(2, cruise.getName());
            statement.setLong(3, cruise.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void delete(Long cruiseId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM CRUISE WHERE id = ?")) {
            statement.setLong(1, cruiseId);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }

    }

    private PreparedStatement statement(Connection connection, Long cruiseId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM CRUISE WHERE id = ?");
        statement.setLong(1, cruiseId);
        return statement;
    }

    private Cruise getCruise(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setId(resultSet.getLong("id"));
        cruise.setShipId(resultSet.getLong("ship_id"));
        cruise.setName(resultSet.getString("name"));
        return cruise;
    }

    private PreparedStatement statementByUser(Connection connection, Long userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND);
        statement.setLong(1, userId);
        return statement;
    }

    private PreparedStatement preparedStatement(Connection connection, Cruise cruise) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO CRUISE (ship_id, name) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, cruise.getShipId());
        statement.setString(2, cruise.getName());
        statement.executeUpdate();
        return statement;
    }
}
