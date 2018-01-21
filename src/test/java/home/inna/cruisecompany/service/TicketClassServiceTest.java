package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.TicketClassDao;
import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.service.impl.TicketClassServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketClassServiceTest {

    @Mock
    private TicketClassDao ticketClassDao;

    @InjectMocks
    private TicketClassService ticketClassService = new TicketClassServiceImpl();

    @Test
    public void findByShip() throws Exception {
        when(ticketClassDao.findByShip(100L)).thenReturn(Collections.emptyList());
        List<TicketClass> ticketClasses = ticketClassService.findByShip(100L);
        assertNotNull(ticketClasses);
        verify(ticketClassDao).findByShip(100L);
    }

    @Test
    public void get() throws Exception {
        final Long TICKET_CLASS_ID = 100L;
        TicketClass mock = new TicketClass();
        mock.setId(TICKET_CLASS_ID);
        when(ticketClassDao.get(TICKET_CLASS_ID)).thenReturn(mock);
        TicketClass ticketClass = ticketClassService.get(TICKET_CLASS_ID);
        assertNotNull(ticketClass);
        assertEquals(TICKET_CLASS_ID, ticketClass.getId());
        verify(ticketClassDao).get(TICKET_CLASS_ID);
    }

    @Test
    public void save() throws Exception {
        TicketClass ticketClass = new TicketClass();
        ticketClassService.saveOrUpdate(ticketClass);
        verify(ticketClassDao).save(ticketClass);
    }

    @Test
    public void update() throws Exception {
        final long TICKET_CLASS_ID = 200L;
        TicketClass ticketClass = new TicketClass();
        ticketClass.setId(TICKET_CLASS_ID);
        ticketClassService.saveOrUpdate(ticketClass);
        verify(ticketClassDao).update(ticketClass);
    }

    @Test
    public void delete() throws Exception {
        final long TICKET_CLASS_ID = 200L;
        ticketClassService.delete(TICKET_CLASS_ID);
        verify(ticketClassDao).delete(TICKET_CLASS_ID);
    }
}