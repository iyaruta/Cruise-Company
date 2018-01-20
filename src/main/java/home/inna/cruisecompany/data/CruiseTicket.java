package home.inna.cruisecompany.data;

import java.math.BigDecimal;

public class CruiseTicket extends TicketClass {

    private Long ticketId;
    private BigDecimal price;
    private int sold;

    public CruiseTicket() {
    }

    public CruiseTicket(TicketClass ticketClass) {
        super(ticketClass);
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
