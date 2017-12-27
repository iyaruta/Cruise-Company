package com.epam.lab.cruisecompany.dao;

import com.epam.lab.cruisecompany.data.Port;

import java.util.List;

public interface PortDao {

    List<Port> findAll();

    Port get(Long portId);

    void save(Port port);

    void update(Port port);

    void delete(Long portId);
}
