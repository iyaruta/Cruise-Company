package com.epam.lab.cruisecompany.data;

import java.time.LocalDateTime;

public class Waypoint {

    private Long id;
    private Long cruiseId;
    private Long portId;
    private LocalDateTime arrival;
    private LocalDateTime departure;

    private Port port;

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

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }
}
