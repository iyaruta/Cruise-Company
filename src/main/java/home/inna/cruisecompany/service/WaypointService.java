package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.Waypoint;

import java.util.List;

public interface WaypointService {

    List<Waypoint> findByCruise(Long cruiseId);

    Waypoint get(Long waypointId);

    void saveOrUpdate(Waypoint waypoint);

    void delete(Long waypointId);
}
