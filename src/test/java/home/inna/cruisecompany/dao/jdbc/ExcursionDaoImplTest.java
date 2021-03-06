package home.inna.cruisecompany.dao.jdbc;

import home.inna.cruisecompany.dao.ExcursionDao;
import home.inna.cruisecompany.dao.TestMode;
import home.inna.cruisecompany.data.Excursion;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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
        assertNotNull(excursion);
        assertEquals(excursionId, excursion.getId());
        assertEquals(portId, excursion.getPortId());
        assertEquals("Old Rotterdam", excursion.getName());
        assertEquals("City overview", excursion.getDetails());
    }

    @Test
    public void save() throws Exception {
        Excursion excursion = new Excursion();
        excursion.setPortId(1L);
        excursion.setName("Young Rotterdam");
        excursion.setDetails("Downtown overview");
        excursion.setPrice(BigDecimal.TEN);
        excursionDao.save(excursion);
    }

    @Test
    public void update() throws Exception {
        Excursion excursion = new Excursion();
        excursion.setId(2L);
        excursion.setPortId(1L);
        excursion.setName("Museum Rotterdam");
        excursion.setDetails("Old City");
        excursionDao.update(excursion);

    }

    @Test
    public void delete() throws Exception {
        excursionDao.delete(Long.MAX_VALUE);
    }

}