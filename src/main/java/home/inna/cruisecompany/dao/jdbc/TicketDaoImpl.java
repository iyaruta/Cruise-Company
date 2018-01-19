package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.TicketDao;
import home.inna.cruisecompany.data.Ticket;
import home.inna.cruisecompany.data.TicketClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private static final Logger LOG = LoggerFactory.getLogger(TicketDaoImpl.class);
    private static final String SQL = "INSERT INTO TICKET(cruise_id, ticket_class_id) VALUES (?, ?)";

    @Override
    public void save(Long cruiseId, List<TicketClass> ticketClasses) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statement(connection, cruiseId, ticketClasses)) {
            statement.executeBatch();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void delete(Long ticketId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM TICKET WHERE id = ?")) {
            statement.setLong(1, ticketId);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Ticket SET price = ? WHERE cruise_id = ? AND ticket_class_id = ?")) {
            statement.setBigDecimal(1, ticket.getPrice());
            statement.setLong(2, ticket.getCruiseId());
            statement.setLong(3, ticket.getTicketClassId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    private Ticket getTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("id"));
        ticket.setCruiseId(resultSet.getLong("cruise_id"));
        ticket.setTicketClassId(resultSet.getLong("ticket_class_id"));
        return ticket;
    }

    private PreparedStatement statement(Connection connection, Long cruiseId, List<TicketClass> ticketClasses) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL);

        for (TicketClass ticketClass : ticketClasses) {
            statement.setLong(1, cruiseId);
            statement.setLong(2, ticketClass.getId());
            statement.addBatch();
        }
        return statement;
    }
}
