package home.inna.cruisecompany.service;

import home.inna.cruisecompany.data.CruiseTicket;

import java.util.List;

public interface CruiseTicketService {

    List<CruiseTicket> find(Long cruiseId);

    void buy(Long userId, Long ticketId);
}
