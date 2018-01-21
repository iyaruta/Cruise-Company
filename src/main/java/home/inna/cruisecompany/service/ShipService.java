package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.Ship;

import java.util.List;

public interface ShipService {

    List<Ship> findAll();

    Ship get(Long shipId);

    void saveOrUpdate(Ship ship);

    void delete(Long shipId);
}
