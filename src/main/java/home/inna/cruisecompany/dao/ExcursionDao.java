package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.Excursion;

import java.util.List;

public interface ExcursionDao {

    List<Excursion> findByPort(Long portId);

    List<Excursion> excursionByUser(Long userId);

    Excursion get(Long excursionId);

    void save(Excursion excursion);

    void update(Excursion excursion);

    void delete(Long excursionId);
}
