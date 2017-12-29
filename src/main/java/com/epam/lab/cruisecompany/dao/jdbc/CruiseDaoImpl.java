package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.ConnectionPool;
import com.epam.lab.cruisecompany.dao.CruiseDao;
import com.epam.lab.cruisecompany.data.Cruise;
import com.epam.lab.cruisecompany.servlet.IndexServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CruiseDaoImpl implements CruiseDao {

    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class);

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
    public void save(Cruise cruise) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO CRUISE (ship_id, name) VALUES (?, ?)")) {
            statement.setLong(1, cruise.getShipId());
            statement.setString(2, cruise.getName());
            statement.executeUpdate();
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
}
