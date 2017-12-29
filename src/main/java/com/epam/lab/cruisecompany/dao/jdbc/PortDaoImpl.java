package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.ConnectionPool;
import com.epam.lab.cruisecompany.dao.PortDao;
import com.epam.lab.cruisecompany.data.Port;
import com.epam.lab.cruisecompany.servlet.IndexServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PortDaoImpl implements PortDao {

    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class);

    @Override
    public List<Port> findAll() {
        List<Port> ports = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM PORT");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Port port = getPort(resultSet);
                ports.add(port);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return ports;
    }

    @Override
    public Port get(Long portId) {
        Port port = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statement(connection, portId);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                port = getPort(resultSet);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return port;
    }

    @Override
    public void save(Port port) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO PORT(name) VALUES (?)")) {
            statement.setString(1, port.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void update(Port port) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE PORT SET name = ?  WHERE id = ?")) {
            statement.setString(1, port.getName());
            statement.setLong(2, port.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }


    }

    @Override
    public void delete(Long portId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM PORT WHERE id = ?")) {
            statement.setLong(1, portId);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    private PreparedStatement statement(Connection connection, Long cruisId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PORT WHERE id = ?");
        statement.setLong(1, cruisId);
        return statement;

    }

    private Port getPort(ResultSet resultSet) throws SQLException {
        Port port = new Port();
        port.setId(resultSet.getLong("id"));
        port.setName(resultSet.getString("name"));
        return port;
    }
}
