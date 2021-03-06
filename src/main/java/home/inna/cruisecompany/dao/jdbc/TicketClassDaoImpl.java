package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.TicketClassDao;
import home.inna.cruisecompany.data.TicketClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketClassDaoImpl implements TicketClassDao {

    private static final Logger LOG = LoggerFactory.getLogger(TicketClassDaoImpl.class);
    private static final String UPDATE = "UPDATE TICKET_CLASS SET count = ?, bonus = ?, type = ? WHERE id = ?";
    private static final String SQL = "INSERT INTO TICKET_CLASS(ship_id, count, bonus, type) VALUES (?, ?, ?, ?)";

    @Override
    public List<TicketClass> findByShip(Long shipId) {
        List<TicketClass> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statementByShip(connection, shipId);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TicketClass ticketClass = getTicketClass(resultSet);
                result.add(ticketClass);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return result;
    }

    @Override
    public TicketClass get(Long id) {
        TicketClass ticketClass = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statement(connection, id);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                ticketClass = getTicketClass(resultSet);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return ticketClass;
    }

    @Override
    public void save(TicketClass ticketClass) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, ticketClass.getShipId());
            statement.setInt(2, ticketClass.getCount());
            statement.setString(3, ticketClass.getBonus());
            statement.setString(4, ticketClass.getType());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void update(TicketClass ticketClass) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, ticketClass.getCount());
            statement.setString(2, ticketClass.getBonus());
            statement.setString(3, ticketClass.getType());
            statement.setLong(4, ticketClass.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM TICKET_CLASS WHERE id =?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    private PreparedStatement statement(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM TICKET_CLASS WHERE id = ?");
        statement.setLong(1, id);
        return statement;
    }

    private TicketClass getTicketClass(ResultSet resultSet) throws SQLException {
        TicketClass ticketClass = new TicketClass();
        ticketClass.setId(resultSet.getLong("id"));
        ticketClass.setShipId(resultSet.getLong("ship_id"));
        ticketClass.setType(resultSet.getString("type"));
        ticketClass.setCount(resultSet.getInt("count"));
        ticketClass.setBonus(resultSet.getString("bonus"));
        return ticketClass;
    }

    private PreparedStatement statementByShip(Connection connection, Long shipId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM TICKET_CLASS WHERE ship_id = ?");
        statement.setLong(1, shipId);
        return statement;
    }
}
