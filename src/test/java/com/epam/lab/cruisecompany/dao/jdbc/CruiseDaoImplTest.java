package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.CruiseDao;
import com.epam.lab.cruisecompany.dao.TestMode;
import com.epam.lab.cruisecompany.data.Cruise;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(cruiseId, cruise.getId());
        assertEquals(shipId, cruise.getShipId());
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