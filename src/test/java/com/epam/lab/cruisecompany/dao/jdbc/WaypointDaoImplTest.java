package com.epam.lab.cruisecompany.dao.jdbc;

import com.epam.lab.cruisecompany.dao.TestMode;
import com.epam.lab.cruisecompany.dao.WaypointDao;
import com.epam.lab.cruisecompany.data.Waypoint;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class WaypointDaoImplTest {

    private WaypointDao waypointDao = new WaypointDaoImpl();

    @Before
    public void before() {
        TestMode.enable();
    }

    @Test
    public void findAll() throws Exception {
        List<Waypoint> waypoints = waypointDao.findByCruise(1L);
        assertNotNull(waypoints);
        assertTrue(waypoints.size() > 0);
        boolean portExist = waypoints.stream().noneMatch(w -> w.getPort() == null);
        assertTrue(portExist);
    }

    @Test
    public void get() throws Exception {
        Long waypointId = 2L;
        Long portId = 1L;
        Long cruiseId = 1L;
        Waypoint waypoint = waypointDao.get(waypointId);
        assertNotNull(waypoint);
        assertEquals(waypointId, waypoint.getId());
        assertEquals(portId, waypoint.getPortId());
        assertEquals(cruiseId, waypoint.getCruiseId());

        LocalDateTime expectedArrival = LocalDateTime.of(2017, 12, 28, 21, 23, 10);
        assertEquals(expectedArrival, waypoint.getArrival());

        LocalDateTime expectedDeparture = LocalDateTime.of(2017, 12, 30, 8, 40, 0);
        assertEquals(expectedDeparture, waypoint.getDeparture());
    }

    @Test
    public void save() throws Exception {
        Waypoint waypoint = new Waypoint();
        waypoint.setPortId(1L);
        waypoint.setCruiseId(1L);

        LocalDateTime expectedArrival = LocalDateTime.of(2017, 12, 30, 20, 20, 10);
        waypoint.setArrival(expectedArrival);

        LocalDateTime expectedDeparture = LocalDateTime.of(2017, 12, 31, 12, 40, 0);
        waypoint.setDeparture(expectedDeparture);
        waypointDao.save(waypoint);
    }

    @Test
    public void update() throws Exception {
        Waypoint waypoint = new Waypoint();
        waypoint.setId(1L);
        waypoint.setPortId(1L);
        waypoint.setCruiseId(1L);

        LocalDateTime expectedArrival = LocalDateTime.of(2017, 12, 30, 23, 0, 0);
        waypoint.setArrival(expectedArrival);

        LocalDateTime expectedDeparture = LocalDateTime.of(2017, 12, 30, 8, 40, 0);
        waypoint.setDeparture(expectedDeparture);
        waypointDao.update(waypoint);
    }

    @Test
    public void delete() throws Exception {
        waypointDao.delete(Long.MAX_VALUE);
    }

}