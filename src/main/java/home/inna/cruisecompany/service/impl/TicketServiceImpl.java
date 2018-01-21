package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.TicketDao;
import home.inna.cruisecompany.dao.jdbc.TicketDaoImpl;
import home.inna.cruisecompany.data.Ticket;
import home.inna.cruisecompany.service.TicketService;

public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao = new TicketDaoImpl();

    @Override
    public void delete(Long ticketId) {
        ticketDao.delete(ticketId);
    }

    @Override
    public void update(Ticket ticket) {
        ticketDao.update(ticket);
    }
}
