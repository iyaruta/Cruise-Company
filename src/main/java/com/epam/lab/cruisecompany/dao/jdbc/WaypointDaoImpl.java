package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.ConnectionPool;
import com.epam.lab.cruisecompany.dao.WaypointDao;
import com.epam.lab.cruisecompany.data.Waypoint;
import com.epam.lab.cruisecompany.servlet.IndexServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaypointDaoImpl implements WaypointDao {

    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class);
    public static final String SQL = "INSERT INTO WAYPOINT(port_id, cruise_id, arrival, departure) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE WAYPOINT SET port_id = ?, cruise_id = ?, arrival = ?, departure = ? WHERE id = ?";

    @Override
    public List<Waypoint> findByCruise(Long cruiseId) {
        //TODO: fix query
        List<Waypoint> waypoints = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statementByCruise(connection, cruiseId);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Waypoint waypoint = getWaypoint(resultSet);
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
            statement.setLong(1, waypoint.getPortId());
            statement.setLong(2, waypoint.getCruiseId());
            statement.setTimestamp(3, Timestamp.valueOf(waypoint.getArrival()));
            statement.setTimestamp(4, Timestamp.valueOf(waypoint.getDeparture()));
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
            statement.setLong(1, waypoint.getPortId());
            statement.setLong(2, waypoint.getCruiseId());
            statement.setTimestamp(3, Timestamp.valueOf(waypoint.getArrival()));
            statement.setTimestamp(4, Timestamp.valueOf(waypoint.getDeparture()));
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

    private PreparedStatement statement(Connection connection, Long waypointId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM WAYPOINT WHERE id = ?");
        statement.setLong(1, waypointId);
        return statement;
    }

    private PreparedStatement statementByCruise(Connection connection, Long cruiseId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM WAYPOINT WHERE cruise_id = ?");
        statement.setLong(1, cruiseId);
        return statement;
    }

    private Waypoint getWaypoint(ResultSet resultSet) throws SQLException {
        Waypoint waypoint = new Waypoint();
        waypoint.setId(resultSet.getLong("id"));
        waypoint.setPortId(resultSet.getLong("port_id"));
        waypoint.setCruiseId(resultSet.getLong("cruise_id"));
        waypoint.setArrival(resultSet.getTimestamp("arrival").toLocalDateTime());
        waypoint.setDeparture(resultSet.getTimestamp("departure").toLocalDateTime());
        return waypoint;
    }
}
