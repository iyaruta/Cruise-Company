package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.Ticket;

public interface TicketService {

    void delete(Long ticketId);

    void update(Ticket ticket);
}
