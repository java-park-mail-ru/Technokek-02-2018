package main.service;

import main.dao.UserDao;
import main.domain.User;
import main.models.Message;
import main.models.PlayerMessage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    public static final long TEST_ID = 8L;
    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;


    @Test
    public void registUser() throws Exception {
        final User newUser = new User( (long) 9,"dsf", "login", "password", null, 0, 0);
        assertEquals(userService.registUser(newUser), new Message<String>(true, "USER_SUCCESSFULLY_REGISTERED"));
        assertEquals(userService.registUser(newUser), new Message<String>(false, "USER_ALREADY_EXISTS"));
    }

    @Test
    public void login() {
    }

    @Test
    public void getUserData() {
    }

    @Test
    public void getScoreBoard() {
    }

    @Test
    public void getPlayer() {

        assertEquals(userService.getPlayer(null), new Message<String>(false, "NOT_LOGINED"));


        Long testId = TEST_ID;
        assertEquals(userService.getPlayer(testId), new Message<String>(false, "INVALID_SESSION_ID"));

        testId = 1L;
        final User testUser = userDao.getById(testId);
        assertEquals(userService.getPlayer(testId), new Message<PlayerMessage>(true, new PlayerMessage(testUser)));

    }

    @Test
    public void editUser() {
    }

    @Test
    public void loguot() {
    }

    @Test
    public void getUsersFromBD() {
    }
}