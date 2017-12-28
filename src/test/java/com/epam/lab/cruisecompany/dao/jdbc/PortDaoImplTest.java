package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.PortDao;
import com.epam.lab.cruisecompany.dao.TestMode;
import com.epam.lab.cruisecompany.data.Port;
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
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}