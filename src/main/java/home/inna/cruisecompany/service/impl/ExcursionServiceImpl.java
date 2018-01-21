package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.ExcursionDao;
import home.inna.cruisecompany.dao.jdbc.ExcursionDaoImpl;
import home.inna.cruisecompany.data.Excursion;
import home.inna.cruisecompany.service.ExcursionService;

import java.util.List;

public class ExcursionServiceImpl implements ExcursionService {

    private ExcursionDao excursionDao = new ExcursionDaoImpl();

    @Override
    public List<Excursion> findByPort(Long portId) {
        return excursionDao.findByPort(portId);
    }

    @Override
    public List<Excursion> excursionByUser(Long userId) {
        return excursionDao.excursionByUser(userId);
    }

    @Override
    public Excursion get(Long excursionId) {
        return excursionDao.get(excursionId);
    }

    @Override
    public void saveOrUpdate(Excursion excursion) {
        if (excursion.getId() == null) {
            excursionDao.save(excursion);
        } else {
            excursion.setId(excursion.getId());
            excursionDao.update(excursion);
        }
    }

    @Override
    public void delete(Long excursionId) {
        excursionDao.delete(excursionId);
    }
}
