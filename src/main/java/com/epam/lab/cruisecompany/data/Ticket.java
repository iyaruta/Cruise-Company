package com.epam.lab.cruisecompany.data;

public class Ticket {

    private Long id;
    private Long cruiseId;
    private Long ticketClassId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Long cruiseId) {
        this.cruiseId = cruiseId;
    }

    public Long getTicketClassId() {
        return ticketClassId;
    }

    public void setTicketClassId(Long ticketClassId) {
        this.ticketClassId = ticketClassId;
    }
}
