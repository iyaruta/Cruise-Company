package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.CruiseDao;
import home.inna.cruisecompany.dao.TicketClassDao;
import home.inna.cruisecompany.data.Cruise;
import home.inna.cruisecompany.data.CruiseTicket;
import home.inna.cruisecompany.data.TicketClass;
import home.inna.cruisecompany.service.impl.CruiseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CruiseServiceTest {

    @Mock
    private CruiseDao cruiseDao;

    @Mock
    private TicketClassDao ticketClassDao;

    @InjectMocks
    private CruiseService cruiseService = new CruiseServiceImpl();

    @Test
    public void save() throws Exception {
        final Long SHIP_ID = 100L;
        TicketClass ticketClass = new TicketClass();
        ticketClass.setShipId(SHIP_ID);
        ticketClass.setType("test_class");

        List<TicketClass> ticketClasses = new ArrayList<>();
        ticketClasses.add(ticketClass);

        when(ticketClassDao.findByShip(SHIP_ID)).thenReturn(ticketClasses);
        assertNotNull(ticketClass);

        Cruise cruise = new Cruise();
        cruise.setShipId(SHIP_ID);

        cruiseService.saveOrUpdate(cruise);
        verify(cruiseDao).save(cruise);
        verify(ticketClassDao).findByShip(SHIP_ID);

        List<CruiseTicket> tickets = cruise.getTickets();
        assertNotNull(tickets);
        assertEquals(1, tickets.size());

        CruiseTicket cruiseTicket = tickets.get(0);
        assertEquals(SHIP_ID, cruiseTicket.getShipId());
        assertEquals("test_class", cruiseTicket.getType());
    }

    @Test
    public void update() throws Exception {
        final long ID = 200L;
        Cruise cruise = new Cruise();
        cruise.setId(ID);
        cruiseService.saveOrUpdate(cruise);
        verify(cruiseDao).update(cruise);
    }

    @Test
    public void findByUser() throws Exception {
        when(cruiseDao.findByUser(100L)).thenReturn(Collections.emptyList());
        List<Cruise> cruises = cruiseService.cruiseByUser(100L);
        assertNotNull(cruises);
        verify(cruiseDao).findByUser(100L);
    }

    @Test
    public void get() throws Exception {
        final Long ID = 100L;
        Cruise mock = new Cruise();
        mock.setId(ID);
        when(cruiseDao.get(ID)).thenReturn(mock);
        Cruise cruise = cruiseService.get(ID);
        assertNotNull(cruise);
        assertEquals(ID, cruise.getId());
        verify(cruiseDao).get(ID);
    }

    @Test
    public void findAll() throws Exception {
        when(cruiseDao.findAll()).thenReturn(Collections.emptyList());
        List<Cruise> cruises = cruiseService.findAll();
        assertNotNull(cruises);
        verify(cruiseDao).findAll();
    }

    @Test
    public void delete() throws Exception {
        final long CRUISE_ID = 200L;
        cruiseService.delete(CRUISE_ID);
        verify(cruiseDao).delete(CRUISE_ID);
    }
}