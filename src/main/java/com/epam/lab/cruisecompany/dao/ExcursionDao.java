package com.epam.lab.cruisecompany.dao;

import com.epam.lab.cruisecompany.data.Excursion;

import java.util.List;

public interface ExcursionDao {

    List<Excursion> findAll();

    Excursion get(Long excursionId);

    void save(Excursion excursion);

    void update(Excursion excursion);

    void delete(Long excursionId);
}
