package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.PortDao;
import home.inna.cruisecompany.dao.TestMode;
import home.inna.cruisecompany.data.Port;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PortDaoImplTest {

    @Before
    public void before() {
        TestMode.enable();
    }

    private PortDao portDao = new PortDaoImpl();

    @Test
    public void findAll() throws Exception {
        List<Port> ports = portDao.findAll();
        assertNotNull(ports);
        assertTrue(ports.size() > 0);
    }

    @Test
    public void get() throws Exception {
        Long portId = 1L;
        Port port = portDao.get(portId);
        assertNotNull(port);
        assertEquals(portId, port.getId());
        assertEquals("Rotterdam", port.getName());
    }

    @Test
    public void save() throws Exception {
        Port port = new Port();
        port.setName("Rotterdam");
        portDao.save(port);
    }

    @Test
    public void update() throws Exception {
        Port port = new Port();
        port.setId(1L);
        port.setName("Rotterdam");
        portDao.update(port);
    }

    @Test
    public void delete() throws Exception {
        portDao.delete(Long.MAX_VALUE);
    }

}