package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.ShipDao;
import com.epam.lab.cruisecompany.data.Ship;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ShipDaoImplTest {

    private ShipDao shipDao = new ShipDaoImpl();

    @Test
    public void findAll() throws Exception {
        List<Ship> ships = shipDao.findAll();
        assertNotNull(ships);
        assertTrue(ships.size() > 0);
    }

    @Test
    public void get() throws Exception {
        Long shipId = 2L;
        Ship ship = shipDao.get(shipId);
        assertNotNull(ship);
        assertEquals(shipId, ship.getId());
        assertEquals("Santa Maria", ship.getName());
        assertEquals(600, ship.getPassengers());
        assertEquals(150, ship.getCrew());
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}