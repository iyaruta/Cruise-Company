package com.epam.lab.cruisecompany.dao;

import com.epam.lab.cruisecompany.data.Cruise;

import java.util.List;

public interface CruiseDao {

    List<Cruise> findAll();

    Cruise get(Long cruiseId);

    void save(Cruise cruise);

    void update(Cruise cruise);

    void delete(Long cruiseId);
}
