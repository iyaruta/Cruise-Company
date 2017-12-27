package com.epam.lab.cruisecompany.dao;

import com.epam.lab.cruisecompany.data.Waypoint;

import java.util.List;

public interface WaypointDao {

    List<Waypoint> findAll();

    Waypoint get(Long waypointId);

    void save(Waypoint waypoint);

    void update(Waypoint waypoint);

    void delete(Long waypointId);
}
