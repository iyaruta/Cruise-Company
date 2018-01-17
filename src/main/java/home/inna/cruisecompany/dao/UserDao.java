package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.User;

public interface UserDao {

    User getByName(String name);

    User get(Long id);

    void save(User user);
}
