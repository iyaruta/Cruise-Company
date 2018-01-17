package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.Waypoint;

import java.util.List;

public interface WaypointDao {

    List<Waypoint> findByCruise(Long cruiseId);

    Waypoint get(Long waypointId);

    void save(Waypoint waypoint);

    void update(Waypoint waypoint);

    void delete(Long waypointId);
}
