package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.Cruise;

public interface CruiseService {

    void saveOrUpdate(Cruise cruise);

    Cruise get(Long id);
}
