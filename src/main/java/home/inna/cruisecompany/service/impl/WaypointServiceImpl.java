package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.WaypointDao;
import home.inna.cruisecompany.dao.jdbc.WaypointDaoImpl;
import home.inna.cruisecompany.data.Waypoint;
import home.inna.cruisecompany.service.WaypointService;

import java.util.List;

public class WaypointServiceImpl implements WaypointService {

    private WaypointDao waypointDao = new WaypointDaoImpl();

    @Override
    public List<Waypoint> findByCruise(Long cruiseId) {
        return waypointDao.findByCruise(cruiseId);
    }

    @Override
    public Waypoint get(Long waypointId) {
        return waypointDao.get(waypointId);
    }

    @Override
    public void saveOrUpdate(Waypoint waypoint) {
        if (waypoint.getId() == null) {
            waypointDao.save(waypoint);
        } else {
            waypoint.setId(waypoint.getId());
            waypointDao.update(waypoint);
        }
    }

    @Override
    public void delete(Long waypointId) {
        waypointDao.delete(waypointId);
    }


}
