package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.Cruise;

import java.util.List;

public interface CruiseService {

    void saveOrUpdate(Cruise cruise);

    List<Cruise> cruiseByUser(Long userId);

    Cruise get(Long id);

    List<Cruise> findAll();

    void delete(Long id);
}
