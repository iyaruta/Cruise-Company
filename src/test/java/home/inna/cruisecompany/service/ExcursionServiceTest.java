package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.ExcursionDao;
import home.inna.cruisecompany.data.Excursion;
import home.inna.cruisecompany.service.impl.ExcursionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExcursionServiceTest {

    @Mock
    private ExcursionDao excursionDao;

    @InjectMocks
    private ExcursionService excursionService = new ExcursionServiceImpl();

    @Test
    public void findByPort() throws Exception {
        when(excursionDao.findByPort(100L)).thenReturn(Collections.emptyList());
        List<Excursion> excursions = excursionService.findByPort(100L);
        assertNotNull(excursions);
        verify(excursionDao).findByPort(100L);
    }

    @Test
    public void excursionByUser() throws Exception {
        when(excursionDao.excursionByUser(100L)).thenReturn(Collections.emptyList());
        List<Excursion> excursions = excursionService.excursionByUser(100L);
        assertNotNull(excursions);
        verify(excursionDao).excursionByUser(100L);

    }

    @Test
    public void get() throws Exception {
        final Long ID = 100L;
        Excursion mock = new Excursion();
        mock.setId(ID);
        when(excursionDao.get(ID)).thenReturn(mock);
        Excursion excursion = excursionService.get(ID);
        assertNotNull(excursion);
        assertEquals(ID, excursion.getId());
        verify(excursionDao).get(ID);
    }

    @Test
    public void save() throws Exception {
        Excursion excursion = new Excursion();
        excursionService.saveOrUpdate(excursion);
        verify(excursionDao).save(excursion);
    }

    @Test
    public void update() throws Exception {
        final long ID = 200L;
        Excursion excursion = new Excursion();
        excursion.setId(ID);
        excursionService.saveOrUpdate(excursion);
        verify(excursionDao).update(excursion);
    }

    @Test
    public void delete() throws Exception {
        final long EXCURSION_ID = 200L;
        excursionService.delete(EXCURSION_ID);
        verify(excursionDao).delete(EXCURSION_ID);
    }
}