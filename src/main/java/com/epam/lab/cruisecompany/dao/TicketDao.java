package com.epam.lab.cruisecompany.dao;

import com.epam.lab.cruisecompany.data.Ticket;

import java.util.List;

public interface TicketDao {

    List<Ticket> findByCruise(Long cruiseId);

//    Ticket get(Long ticketId);

    void save(Ticket ticket);

//    void update(Ticket ticket);

    void delete(Long ticketId);
}
