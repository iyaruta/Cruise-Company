package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.Port;

import java.util.List;

public interface PortDao {

    List<Port> findAll();

    Port get(Long portId);

    void save(Port port);

    void update(Port port);

    void delete(Long portId);
}
