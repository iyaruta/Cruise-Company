package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.ExcursionDao;
import com.epam.lab.cruisecompany.dao.TestMode;
import com.epam.lab.cruisecompany.data.Excursion;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExcursionDaoImplTest {

    @Before
    public void before() {
        TestMode.enable();
    }

    private ExcursionDao excursionDao = new ExcursionDaoImpl();

    @Test
    public void findByPort() throws Exception {
        Long portId = 1L;
        List<Excursion> excursions = excursionDao.findByPort(portId);
        assertNotNull(excursions);
        assertTrue(excursions.size() > 0);
    }

    @Test
    public void get() throws Exception {
        Long excursionId = 1L;
        Long portId = 1L;
        Excursion excursion = excursionDao.get(excursionId);
        assertEquals(excursionId, excursion.getId());
        assertEquals(portId, excursion.getPortId());
        assertEquals("Old Rotterdam", excursion.getName());
        assertEquals("City overview", excursion.getDetails());
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