package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.UserDao;
import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    public void getByName() throws Exception {
        final String name = "Ivan";
        User mock = new User();
        mock.setName(name);
        when(userDao.getByName("Ivan")).thenReturn(mock);
        User user = userService.getByName("Ivan");
        assertNotNull(user);
        verify(userDao).getByName("Ivan");
    }

    @Test
    public void get() throws Exception {
        final Long ID = 100L;
        User mock = new User();
        mock.setId(ID);
        when(userDao.get(ID)).thenReturn(mock);
        User user = userService.get(ID);
        assertNotNull(user);
        assertEquals(ID, user.getId());
        verify(userDao).get(ID);
    }

    @Test
    public void save() throws Exception {
        final long USER_ID = 200L;
        User user = new User();
        user.setId(USER_ID);
        userService.save(user);

        verify(userDao).save(user);
    }
}