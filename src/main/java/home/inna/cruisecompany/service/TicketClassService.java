package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.TicketClass;

import java.util.List;

public interface TicketClassService {

    List<TicketClass> findByShip(Long shipId);

    TicketClass get(Long id);

    void saveOrUpdate(TicketClass ticketClass);

    void delete(Long id);
}
