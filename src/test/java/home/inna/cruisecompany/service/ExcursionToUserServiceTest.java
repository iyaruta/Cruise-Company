package home.inna.cruisecompany.service;

import home.inna.cruisecompany.dao.ExcursionToUserDao;
import home.inna.cruisecompany.data.Excursion;
import home.inna.cruisecompany.data.User;
import home.inna.cruisecompany.service.impl.ExcursionToUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ExcursionToUserServiceTest {

    @Mock
    private ExcursionToUserDao excursionToUserDao;

    @InjectMocks
    private ExcursionToUserService excursionToUserService = new ExcursionToUserServiceImpl();

    @Test
    public void buy() throws Exception {
        final Long USER_ID = 1L;
        final Long EXCURSION_ID = 1L;
        Excursion mock = new Excursion();
        mock.setId(EXCURSION_ID);
        User userMock = new User();
        userMock.setId(USER_ID);
        excursionToUserService.buy(EXCURSION_ID, USER_ID);
        verify(excursionToUserDao).buy(EXCURSION_ID, USER_ID);
    }
}