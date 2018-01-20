package home.inna.cruisecompany.data;

public class TicketClass {

    private Long id;
    private Long shipId;
    private String type;
    private int count;
    private String bonus;

    public TicketClass() {
    }

    public TicketClass(TicketClass ticketClass) {
        this.id = ticketClass.id;
        this.shipId = ticketClass.shipId;
        this.type = ticketClass.type;
        this.count = ticketClass.count;
        this.bonus = ticketClass.bonus;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

}
