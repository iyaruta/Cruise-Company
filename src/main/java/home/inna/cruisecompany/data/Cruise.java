package home.inna.cruisecompany.data;

import java.util.List;

public class Cruise {

    private Long id;
    private Long shipId;
    private String name;

    private List<CruiseTicket> tickets;
    private List<Waypoint> waypoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CruiseTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<CruiseTicket> tickets) {
        this.tickets = tickets;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }
}
