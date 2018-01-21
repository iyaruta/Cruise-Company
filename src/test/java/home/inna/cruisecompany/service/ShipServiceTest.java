package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.ShipDao;
import home.inna.cruisecompany.data.Ship;
import home.inna.cruisecompany.service.impl.ShipServiceImpl;
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
public class ShipServiceTest {

    @Mock
    private ShipDao shipDao;

    @InjectMocks
    private ShipService shipService = new ShipServiceImpl();

    @Test
    public void findAll() throws Exception {
        when(shipDao.findAll()).thenReturn(Collections.emptyList());
        List<Ship> ships = shipService.findAll();
        assertNotNull(ships);
        verify(shipDao).findAll();
    }

    @Test
    public void get() throws Exception {
        final Long ID = 100L;
        Ship mock = new Ship();
        mock.setId(ID);
        when(shipDao.get(ID)).thenReturn(mock);
        Ship ship = shipService.get(ID);
        assertNotNull(ship);
        assertEquals(ID, ship.getId());
        verify(shipDao).get(ID);
    }

    @Test
    public void save() throws Exception {
        Ship ship = new Ship();
        shipService.saveOrUpdate(ship);
        verify(shipDao).save(ship);
    }

    @Test
    public void update() throws Exception {
        final long ID = 200L;
        Ship ship = new Ship();
        ship.setId(ID);
        shipService.saveOrUpdate(ship);
        verify(shipDao).update(ship);
    }

    @Test
    public void delete() throws Exception {
        final long SHIP_ID = 200L;
        shipService.delete(SHIP_ID);
        verify(shipDao).delete(SHIP_ID);
    }
}