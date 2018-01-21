package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.Port;

import java.util.List;

public interface PortService {

    List<Port> findAll();

    Port get(Long portId);

    void saveOrUpdate(Port port);

    void delete(Long portId);
}
