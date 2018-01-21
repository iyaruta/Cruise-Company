package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.TicketClassDao;
import home.inna.cruisecompany.dao.jdbc.TicketClassDaoImpl;
import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.service.TicketClassService;

import java.util.List;

public class TicketClassServiceImpl implements TicketClassService {

    private TicketClassDao ticketClassDao = new TicketClassDaoImpl();

    @Override
    public List<TicketClass> findByShip(Long shipId) {
        return ticketClassDao.findByShip(shipId);
    }

    @Override
    public TicketClass get(Long id) {
        return ticketClassDao.get(id);
    }

    @Override
    public void saveOrUpdate(TicketClass ticketClass) {
        if (ticketClass.getId() == null) {
            ticketClassDao.save(ticketClass);
        } else {
            ticketClass.setId(ticketClass.getId());
            ticketClassDao.update(ticketClass);
        }
    }

    @Override
    public void delete(Long id) {
        ticketClassDao.delete(id);
    }
}
