package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.TicketDao;
import home.inna.cruisecompany.data.Ticket;
import home.inna.cruisecompany.service.impl.TicketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketService ticketService = new TicketServiceImpl();

    @Test
    public void delete() throws Exception {
        final long TICKET_ID = 200L;
        ticketService.delete(TICKET_ID);
        verify(ticketDao).delete(TICKET_ID);
    }

    @Test
    public void update() throws Exception {
        final long TICKET_ID = 200L;
        Ticket ticket = new Ticket();
        ticket.setId(TICKET_ID);
        ticket.setCruiseId(100L);
        ticket.setTicketClassId(100L);
        ticketService.update(ticket);

        verify(ticketDao).update(ticket);
    }
}