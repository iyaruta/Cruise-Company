package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.Ticket;

public interface TicketDao {

    void delete(Long ticketId);

    void update(Ticket ticket);
}
