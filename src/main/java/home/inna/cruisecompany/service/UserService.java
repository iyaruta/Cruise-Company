package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.User;

public interface UserService {

    User getByName(String name);

    User get(Long id);

    void save(User user);
}
