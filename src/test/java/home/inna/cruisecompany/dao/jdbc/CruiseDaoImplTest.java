package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.CruiseDao;
import home.inna.cruisecompany.dao.TestMode;
import home.inna.cruisecompany.data.Cruise;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CruiseDaoImplTest {

    @Before
    public void before() {
        TestMode.enable();
    }

    private CruiseDao cruiseDao = new CruiseDaoImpl();

    @Test
    public void findAll() throws Exception {
        List<Cruise> cruises = cruiseDao.findAll();
        assertNotNull(cruises);
        assertTrue(cruises.size() > 0);
    }

    @Test
    public void get() throws Exception {
        Long cruiseId = 1L;
        Long shipId = 1L;
        Cruise cruise = cruiseDao.get(cruiseId);
        assertNotNull(cruise);
        assertEquals(cruiseId, cruise.getId());
        assertEquals(shipId, cruise.getShipId());
        assertEquals("Atlantic Dream", cruise.getName());
    }

    @Test
    public void save() throws Exception {
        Cruise cruise = new Cruise();
        cruise.setShipId(2L);
        cruise.setName("Faraway island");
        cruise.setTickets(Collections.emptyList());
        cruise.setWaypoints(Collections.emptyList());
        cruiseDao.save(cruise);
    }

    @Test
    public void update() throws Exception {
        Cruise cruise = new Cruise();
        cruise.setId(1L);
        cruise.setShipId(2L);
        cruise.setName("Near island");
        cruiseDao.update(cruise);
    }

    @Test
    public void delete() throws Exception {
        cruiseDao.delete(Long.MAX_VALUE);
    }

}