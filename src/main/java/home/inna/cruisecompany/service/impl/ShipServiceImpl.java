package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.ShipDao;
import home.inna.cruisecompany.dao.jdbc.ShipDaoImpl;
import home.inna.cruisecompany.data.Ship;
import home.inna.cruisecompany.service.ShipService;

import java.util.List;

public class ShipServiceImpl implements ShipService {

    private ShipDao shipDao = new ShipDaoImpl();

    @Override
    public List<Ship> findAll() {
        return shipDao.findAll();
    }

    @Override
    public Ship get(Long shipId) {
        return shipDao.get(shipId);
    }

    @Override
    public void saveOrUpdate(Ship ship) {
        if (ship.getId() == null) {
            shipDao.save(ship);
        } else {
            ship.setId(ship.getId());
            shipDao.update(ship);
        }
    }

    @Override
    public void delete(Long shipId) {
        shipDao.delete(shipId);
    }
}
