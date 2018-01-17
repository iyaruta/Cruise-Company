package com.epam.lab.cruisecompany.dao;

import com.epam.lab.cruisecompany.data.User;

public interface UserDao {

    User getByName(String name);

    User get(Long id);

    void save(User user);
}
