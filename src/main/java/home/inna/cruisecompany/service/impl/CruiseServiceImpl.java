package home.inna.cruisecompany.service.impl;

import home.inna.cruisecompany.dao.CruiseDao;
import home.inna.cruisecompany.dao.TicketClassDao;
import home.inna.cruisecompany.dao.jdbc.CruiseDaoImpl;
import home.inna.cruisecompany.dao.jdbc.TicketClassDaoImpl;
import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.data.CruiseTicket;
import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.service.CruiseService;

import java.util.List;
import java.util.stream.Collectors;

public class CruiseServiceImpl implements CruiseService {

    private CruiseDao cruiseDao = new CruiseDaoImpl();
    private TicketClassDao ticketClassDao = new TicketClassDaoImpl();

    @Override
    public void saveOrUpdate(Cruise cruise) {
        if (cruise.getId() == null) {
            List<TicketClass> ticketClasses = ticketClassDao.findByShip(cruise.getShipId());

            List<CruiseTicket> tickets = ticketClasses.stream().map(CruiseTicket::new).collect(Collectors.toList());
            cruise.setTickets(tickets);


            cruiseDao.save(cruise);
        } else {
            cruiseDao.update(cruise);
        }
    }

    @Override
    public Cruise get(Long id) {
        return cruiseDao.get(id);
    }
}
