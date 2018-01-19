package home.inna.cruisecompany.dao;

import home.inna.cruisecompany.data.CruiseTicket;

import java.util.List;

public interface CruiseTicketDao {

    List<CruiseTicket> find(Long cruiseId);

    void buy(Long userId, Long ticketId);
}
