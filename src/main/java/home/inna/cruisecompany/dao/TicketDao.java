package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.Ticket;
import home.inna.cruisecompany.data.TicketClass;

import java.util.List;

public interface TicketDao {

    void save(Long cruiseId, List<TicketClass> ticketClasses);

    void delete(Long ticketId);

    void update(Ticket ticket);
}
