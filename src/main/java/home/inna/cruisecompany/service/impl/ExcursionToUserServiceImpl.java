package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.ExcursionToUserDao;
import home.inna.cruisecompany.dao.jdbc.ExcursionToUserDaoImpl;
import home.inna.cruisecompany.service.ExcursionToUserService;

public class ExcursionToUserServiceImpl implements ExcursionToUserService {

    private ExcursionToUserDao excursionDaoTuUserDao = new ExcursionToUserDaoImpl();

    @Override
    public void buy(Long excursionId, Long userId) {
        excursionDaoTuUserDao.buy(excursionId, userId);
    }
}
