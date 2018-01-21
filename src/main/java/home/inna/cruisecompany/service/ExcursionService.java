package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.Excursion;

import java.util.List;

public interface ExcursionService {

    List<Excursion> findByPort(Long portId);

    List<Excursion> excursionByUser(Long userId);

    Excursion get(Long excursionId);

    void saveOrUpdate(Excursion excursion);

    void delete(Long excursionId);
}
