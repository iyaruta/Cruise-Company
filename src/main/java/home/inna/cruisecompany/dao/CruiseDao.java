package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.Cruise;

import java.util.List;

public interface CruiseDao {

    List<Cruise> findAll();

    Cruise get(Long cruiseId);

    Long save(Cruise cruise);

    void update(Cruise cruise);

    void delete(Long cruiseId);
}
