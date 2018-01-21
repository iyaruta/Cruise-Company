package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.PortDao;
import home.inna.cruisecompany.data.Port;
import home.inna.cruisecompany.service.impl.PortServiceImpl;
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
public class PortServiceTest {

    @Mock
    private PortDao portDao;

    @InjectMocks
    private PortService portService = new PortServiceImpl();

    @Test
    public void findAll() throws Exception {
        when(portDao.findAll()).thenReturn(Collections.emptyList());
        List<Port> ports = portService.findAll();
        assertNotNull(ports);
        verify(portDao).findAll();
    }

    @Test
    public void get() throws Exception {
        final Long ID = 100L;
        Port mock = new Port();
        mock.setId(ID);
        when(portDao.get(ID)).thenReturn(mock);
        Port port = portService.get(ID);
        assertNotNull(port);
        assertEquals(ID, port.getId());
        verify(portDao).get(ID);
    }

    @Test
    public void save() throws Exception {
        Port port = new Port();
        portService.saveOrUpdate(port);
        verify(portDao).save(port);
    }

    @Test
    public void update() throws Exception {
        final long ID = 200L;
        Port port = new Port();
        port.setId(ID);
        portService.saveOrUpdate(port);
        verify(portDao).update(port);
    }

    @Test
    public void delete() throws Exception {
        final long PORT_ID = 200L;
        portService.delete(PORT_ID);
        verify(portDao).delete(PORT_ID);
    }
}