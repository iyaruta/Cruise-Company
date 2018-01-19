package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.CruiseTicketDao;
import home.inna.cruisecompany.data.CruiseTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CruiseTicketDaoImpl implements CruiseTicketDao {

    private static final Logger LOG = LoggerFactory.getLogger(CruiseTicketDaoImpl.class);

    private static final String FIND_BY_CRUISE = "SELECT tc.*, t.id as ticket_id, t.price, count(utt.ticket_id) as sold FROM TICKET_CLASS tc \n" +
            "INNER JOIN Ticket t ON t.ticket_class_id = tc.id \n " +
            "INNER JOIN Cruise c ON t.cruise_id = c.id \n" +
            "LEFT JOIN USER_TO_TICKET utt ON t.id = utt.ticket_id \n" +
            "WHERE c.id = ? \n" +
            "GROUP BY tc.id, t.id";

    @Override
    public List<CruiseTicket> find(Long cruiseId) {
        List<CruiseTicket> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statementByCruise(connection, cruiseId);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                CruiseTicket ticket = getCruiseTicket(resultSet);
                result.add(ticket
                );
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return result;
    }

    @Override
    public void buy(Long userId, Long ticketId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO USER_TO_TICKET(user_id, ticket_id) VALUES (?, ?)")) {
            statement.setLong(1, userId);
            statement.setLong(2, ticketId);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    private PreparedStatement statementByCruise(Connection connection, Long cruiseId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_CRUISE);
        statement.setLong(1, cruiseId);
        return statement;
    }

    private CruiseTicket getCruiseTicket(ResultSet resultSet) throws SQLException {
        CruiseTicket ticket = new CruiseTicket();
        ticket.setId(resultSet.getLong("id"));
        ticket.setShipId(resultSet.getLong("ship_id"));
        ticket.setType(resultSet.getString("type"));
        ticket.setCount(resultSet.getInt("count"));
        ticket.setBonus(resultSet.getString("bonus"));
        ticket.setTicketId(resultSet.getLong("ticket_id"));
        ticket.setPrice(resultSet.getBigDecimal("price"));
        ticket.setSold(resultSet.getInt("sold"));
        return ticket;
    }
}
