package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.Excursion;

import java.util.List;

public interface ExcursionDao {

    List<Excursion> findByPort(Long portId);

    Excursion get(Long excursionId);

    void save(Excursion excursion);

    void update(Excursion excursion);

    void delete(Long excursionId);
}
