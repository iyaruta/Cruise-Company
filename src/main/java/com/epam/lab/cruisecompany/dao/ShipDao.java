package com.epam.lab.cruisecompany.dao;

import com.epam.lab.cruisecompany.data.Ship;

import java.util.List;

public interface ShipDao {

    List<Ship> findAll();

    Ship get(Long shipId);

    void save(Ship ship);

    void update(Ship ship);

    void delete(Long shipId);
}
