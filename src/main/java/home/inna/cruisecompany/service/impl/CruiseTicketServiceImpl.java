package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.CruiseTicketDao;
import home.inna.cruisecompany.dao.jdbc.CruiseTicketDaoImpl;
import home.inna.cruisecompany.data.CruiseTicket;
import home.inna.cruisecompany.service.CruiseTicketService;

import java.util.List;

public class CruiseTicketServiceImpl implements CruiseTicketService {

    private CruiseTicketDao cruiseTicketDao = new CruiseTicketDaoImpl();

    @Override
    public List<CruiseTicket> find(Long cruiseId) {
        return cruiseTicketDao.find(cruiseId);
    }

    @Override
    public void buy(Long userId, Long ticketId) {
        cruiseTicketDao.buy(userId, ticketId);
    }
}
