package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.TicketDao;
import home.inna.cruisecompany.data.Ticket;
import home.inna.cruisecompany.servlet.IndexServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class);
    private static final String SQL = "INSERT INTO TICKET(id, cruise_id, ticket_class_id) VALUES (?, ?, ?)";

    @Override
    public List<Ticket> findByCruise(Long cruiseId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statementByCruise(connection, cruiseId);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return tickets;
    }

    @Override
    public void save(Ticket ticket) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, ticket.getId());
            statement.setLong(2, ticket.getCruiseId());
            statement.setLong(3, ticket.getTicketClassId());
            statement.executeUpdate();
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

    private PreparedStatement statementByCruise(Connection connection, Long cruiseId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT t.*, p.name FROM WAYPOINT w " +
                " INNER JOIN PORT p ON w.port_id  = p.id WHERE cruise_id = ?");
        statement.setLong(1, cruiseId);
        return statement;
    }

    private Ticket getTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("id"));
        ticket.setCruiseId(resultSet.getLong("cruise_id"));
        ticket.setTicketClassId(resultSet.getLong("ticket_class_id"));
        return ticket;
    }
}
