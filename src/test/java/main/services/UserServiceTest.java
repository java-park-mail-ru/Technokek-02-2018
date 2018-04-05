package main.services;

import main.dao.UserDao;
import main.domain.User;
import main.models.Error.ErrorStackMessages;
import main.models.Error.ErrorTypes;
import main.models.Message;
import main.models.PlayerMessage;
import main.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

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

    @Mock
    private HttpSession httpSession;


    @Test
    public void registUser() throws Exception {
        final User newUser = new User( (long) 9,"dsf", "login", "password", null, 0, 0);

        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        errorStackMessages.addFieldError("email", ErrorTypes.ERRORS_MAP.get(ErrorTypes.USER_ALREADY_EXISTS));

        assertEquals(userService.registUser(newUser, httpSession), new Message<>(true, new PlayerMessage(newUser)));
        assertEquals(userService.registUser(newUser, httpSession), new Message<>(false, errorStackMessages));
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

        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));

        assertEquals(userService.getPlayer(null), new Message<>(false, errorStackMessages));


        Long testId = TEST_ID;
        assertEquals(userService.getPlayer(testId), new Message<>(false, errorStackMessages));

        testId = 1L;
        final User testUser = userDao.getById(testId);
        assertEquals(userService.getPlayer(testId), new Message<>(true, new PlayerMessage(testUser)));

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