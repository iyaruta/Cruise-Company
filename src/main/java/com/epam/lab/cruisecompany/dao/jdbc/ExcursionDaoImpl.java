package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.ConnectionPool;
import com.epam.lab.cruisecompany.dao.ExcursionDao;
import com.epam.lab.cruisecompany.data.Excursion;
import com.epam.lab.cruisecompany.servlet.IndexServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDaoImpl implements ExcursionDao {

    private static final Logger LOG = LoggerFactory.getLogger(IndexServlet.class);
    private static final String SQL = "INSERT INTO EXCURSION (port_id, name, details) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE EXCURSION SET port_id = ?, name = ?, details = ? WHERE id = ?";

    @Override
    public List<Excursion> findByPort(Long portId) {
        //TODO: fix query
        List<Excursion> excursions = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statementByPort(connection, portId);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Excursion excursion = getExcursion(resultSet);
                excursions.add(excursion);
            }
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return excursions;
    }

    @Override
    public Excursion get(Long excursionId) {
        Excursion excursion = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = statement(connection, excursionId);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                excursion = getExcursion(resultSet);
            }

        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
        return excursion;
    }

    @Override
    public void save(Excursion excursion) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, excursion.getPortId());
            statement.setString(2, excursion.getName());
            statement.setString(3, excursion.getDetails());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void update(Excursion excursion) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, excursion.getPortId());
            statement.setString(2, excursion.getName());
            statement.setString(3, excursion.getDetails());
            statement.setLong(4, excursion.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    @Override
    public void delete(Long excursionId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM EXCURSION WHERE id = ?")) {
            statement.setLong(1, excursionId);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("SQL error", e);
            throw new IllegalStateException("SQL error", e);
        }
    }

    private PreparedStatement statement(Connection connection, Long excursionId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM EXCURSION WHERE id = ?");
        statement.setLong(1, excursionId);
        return statement;
    }

    private PreparedStatement statementByPort(Connection connection, Long portId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM EXCURSION WHERE port_id = ?");
        statement.setLong(1, portId);
        return statement;
    }

    private Excursion getExcursion(ResultSet resultSet) throws SQLException {
        Excursion excursion = new Excursion();
        excursion.setId(resultSet.getLong("id"));
        excursion.setPortId(resultSet.getLong("port_id"));
        excursion.setName(resultSet.getString("name"));
        excursion.setDetails(resultSet.getString("details"));
        return excursion;
    }
}
