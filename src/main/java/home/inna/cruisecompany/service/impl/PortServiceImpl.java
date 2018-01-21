package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.PortDao;
import home.inna.cruisecompany.dao.jdbc.PortDaoImpl;
import home.inna.cruisecompany.data.Port;
import home.inna.cruisecompany.service.PortService;

import java.util.List;

public class PortServiceImpl implements PortService {

    private PortDao portDao = new PortDaoImpl();

    @Override
    public List<Port> findAll() {
        return portDao.findAll();
    }

    @Override
    public Port get(Long portId) {
        return portDao.get(portId);
    }

    @Override
    public void saveOrUpdate(Port port) {
        if (port.getId() == null) {
            portDao.save(port);
        } else {
            port.setId(port.getId());
            portDao.update(port);
        }
    }

    @Override
    public void delete(Long portId) {
        portDao.delete(portId);
    }
}
