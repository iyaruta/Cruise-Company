package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ConnectionPool;
import home.inna.cruisecompany.dao.ShipDao;
import home.inna.cruisecompany.data.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipDaoImpl implements ShipDao {

    private static final Logger LOG = LoggerFactory.getLogger(ShipDaoImpl.class);
    public static final String INSERT_SQL = "INSERT INTO SHIP(name, passengers, crew) VALUES (?, ?, ?)";

    @Override
    public List<Ship> findAll() {
        List<Ship> ships = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM SHIP");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ship ship = new Ship();
                ship.setId(resultSet.getLong("id"));
                ship.setName(resultSet.getString("name"));
                ship.setPassengers(resultSet.getInt("passengers"));
                ship.setCrew(resultSet.getInt("crew"));
                ships.add(ship);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return ships;
    }

    @Override
    public Ship get(Long shipId) {
        Ship ship = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statement(connection, shipId);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                ship = new Ship();
                ship.setId(resultSet.getLong("id"));
                ship.setName(resultSet.getString("name"));
                ship.setPassengers(resultSet.getInt("passengers"));
                ship.setCrew(resultSet.getInt("crew"));
            }

        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return ship;
    }

    @Override
    public void save(Ship ship) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, ship.getName());
            statement.setInt(2, ship.getPassengers());
            statement.setInt(3, ship.getCrew());
            statement.executeUpdate();

        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void update(Ship ship) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE SHIP SET name = ?, passengers = ?, crew = ? WHERE id = ?")) {
            statement.setString(1, ship.getName());
            statement.setInt(2, ship.getPassengers());
            statement.setInt(3, ship.getCrew());
            statement.setLong(4, ship.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void delete(Long shipId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM SHIP WHERE id = ?")) {
            statement.setLong(1, shipId);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    private PreparedStatement statement(Connection connection, Long shipId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM SHIP WHERE ID = ? ");
        statement.setLong(1, shipId);
        return statement;
    }
}


