package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.WaypointDao;
import home.inna.cruisecompany.data.Port;
import home.inna.cruisecompany.data.Waypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaypointDaoImpl implements WaypointDao {

    private static final Logger LOG = LoggerFactory.getLogger(WaypointDaoImpl.class);
    public static final String SQL = "INSERT INTO WAYPOINT(port_id, cruise_id, arrival, departure) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE WAYPOINT SET port_id = ?, cruise_id = ?, arrival = ?, departure = ? WHERE id = ?";

    @Override
    public List<Waypoint> findByCruise(Long cruiseId) {
        List<Waypoint> waypoints = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statementByCruise(connection, cruiseId);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Waypoint waypoint = getWaypoint(resultSet);
                Port port = new Port();
                port.setId(waypoint.getPortId());
                port.setName(resultSet.getString("name"));
                waypoint.setPort(port);
                waypoints.add(waypoint);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return waypoints;
    }

    @Override
    public Waypoint get(Long waypointId) {
        Waypoint waypoint = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statement(connection, waypointId);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                waypoint = getWaypoint(resultSet);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return waypoint;
    }

    @Override
    public void save(Waypoint waypoint) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            apply(waypoint, statement);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }

    }

    @Override
    public void update(Waypoint waypoint) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            apply(waypoint, statement);
            statement.setLong(5, waypoint.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }

    }

    @Override
    public void delete(Long waypointId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM WAYPOINT WHERE id =?")) {
            statement.setLong(1, waypointId);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }

    }

    private void apply(Waypoint waypoint, PreparedStatement statement) throws SQLException {
        statement.setLong(1, waypoint.getPortId());
        statement.setLong(2, waypoint.getCruiseId());
        if (waypoint.getArrival() == null) {
            statement.setTimestamp(3, null);
        } else {
            statement.setTimestamp(3, Timestamp.valueOf(waypoint.getArrival()));
        }

        if (waypoint.getDeparture() == null) {
            statement.setTimestamp(4, null);
        } else {
            statement.setTimestamp(4, Timestamp.valueOf(waypoint.getDeparture()));
        }
    }

    private PreparedStatement statement(Connection connection, Long waypointId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM WAYPOINT WHERE id = ?");
        statement.setLong(1, waypointId);
        return statement;
    }

    private PreparedStatement statementByCruise(Connection connection, Long cruiseId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT w.*, p.name FROM WAYPOINT w " +
                " INNER JOIN PORT p ON w.port_id  = p.id WHERE cruise_id = ? order by arrival");
        statement.setLong(1, cruiseId);
        return statement;
    }

    private Waypoint getWaypoint(ResultSet resultSet) throws SQLException {
        Waypoint waypoint = new Waypoint();
        waypoint.setId(resultSet.getLong("id"));
        waypoint.setPortId(resultSet.getLong("port_id"));
        waypoint.setCruiseId(resultSet.getLong("cruise_id"));
        Timestamp arrival = resultSet.getTimestamp("arrival");
        waypoint.setArrival(arrival == null ? null : arrival.toLocalDateTime());
        Timestamp departure = resultSet.getTimestamp("departure");
        waypoint.setDeparture(departure == null ? null : departure.toLocalDateTime());
        return waypoint;
    }
}
