package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.UserDao;
import home.inna.cruisecompany.dao.jdbc.UserDaoImpl;
import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
