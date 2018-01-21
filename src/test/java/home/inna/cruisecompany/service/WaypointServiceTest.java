package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.WaypointDao;
import home.inna.cruisecompany.data.Waypoint;
import home.inna.cruisecompany.service.impl.WaypointServiceImpl;
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
public class WaypointServiceTest {

    @Mock
    private WaypointDao waypointDao;

    @InjectMocks
    private WaypointService waypointService = new WaypointServiceImpl();

    @Test
    public void findByCruise() throws Exception {
        when(waypointDao.findByCruise(100L)).thenReturn(Collections.emptyList());
        List<Waypoint> waypoints = waypointService.findByCruise(100L);
        assertNotNull(waypoints);
        verify(waypointDao).findByCruise(100L);
    }

    @Test
    public void get() throws Exception {
        final Long ID = 100L;
        Waypoint mock = new Waypoint();
        mock.setId(ID);
        when(waypointDao.get(ID)).thenReturn(mock);
        Waypoint waypoint = waypointService.get(ID);
        assertNotNull(waypoint);
        assertEquals(ID, waypoint.getId());
        verify(waypointDao).get(ID);
    }

    @Test
    public void save() throws Exception {
        final long CRUISE_ID = 200L;
        Waypoint waypoint = new Waypoint();
        waypoint.setCruiseId(CRUISE_ID);
        waypointService.saveOrUpdate(waypoint);

        verify(waypointDao).save(waypoint);
    }

    @Test
    public void update() throws Exception {
        final long ID = 200L;
        Waypoint waypoint = new Waypoint();
        waypoint.setId(ID);
        waypointService.saveOrUpdate(waypoint);
        verify(waypointDao).update(waypoint);
    }

    @Test
    public void delete() throws Exception {
        final long PORT_ID = 200L;
        waypointService.delete(PORT_ID);
        verify(waypointDao).delete(PORT_ID);
    }
}