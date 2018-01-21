package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.CruiseTicketDao;
import home.inna.cruisecompany.data.CruiseTicket;
import home.inna.cruisecompany.service.impl.CruiseTicketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CruiseTicketServiceTest {

    @Mock
    private CruiseTicketDao cruiseTicketDao;

    @InjectMocks
    private CruiseTicketService cruiseTicketService = new CruiseTicketServiceImpl();

    @Test
    public void find() throws Exception {
        when(cruiseTicketDao.find(100L)).thenReturn(Collections.emptyList());
        List<CruiseTicket> cruisesTicket = cruiseTicketService.find(100L);
        assertNotNull(cruisesTicket);
        verify(cruiseTicketDao).find(100L);
    }

    @Test
    public void buy() throws Exception {
        final Long USER_ID = 150L;
        final Long TICKET_ID = 250L;
        cruiseTicketService.buy(USER_ID, TICKET_ID);
        verify(cruiseTicketDao).buy(USER_ID, TICKET_ID);
    }
}