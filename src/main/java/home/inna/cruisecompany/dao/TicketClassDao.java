package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.TicketClass;

import java.util.List;

public interface TicketClassDao {

    List<TicketClass> findByShip(Long shipId);

    List<TicketClass> findByCruise(Long cruiseId);

    TicketClass get(Long id);

    void save(TicketClass ticketClass);

    void update(TicketClass ticketClass);

    void delete(Long id);
}
