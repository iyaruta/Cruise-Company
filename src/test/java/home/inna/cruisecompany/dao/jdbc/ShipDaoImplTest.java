package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ShipDao;
import home.inna.cruisecompany.dao.TestMode;
import home.inna.cruisecompany.data.Ship;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ShipDaoImplTest {

    private ShipDao shipDao = new ShipDaoImpl();

    @Before
    public void before() {
        TestMode.enable();
    }

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
        Ship ship = new Ship();
        ship.setName("Titanic");
        ship.setPassengers(2000);
        ship.setCrew(800);
        shipDao.save(ship);
    }

    @Test
    public void update() throws Exception {
        Ship ship = new Ship();
        ship.setId(1L);
        ship.setName("Titanic");
        ship.setPassengers(1500);
        ship.setCrew(200);
        shipDao.update(ship);
    }

    @Test
    public void delete() throws Exception {
        shipDao.delete(Long.MAX_VALUE);
    }
}