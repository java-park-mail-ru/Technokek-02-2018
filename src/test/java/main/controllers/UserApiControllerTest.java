package main.controllers;

import main.models.Message;
import main.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import javax.servlet.http.HttpSession;

@RunWith(MockitoJUnitRunner.class)
public class UserApiControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    UserApiController userApiController;


    @Test
    public void logout() {
        final Message message = userApiController.logout(null);
        verify(userService).loguot(null);
    }

    @Test
    public void getMe() {
        final Message message = userApiController.getMe(null);
        verify(userService).getUserData(null);
    }

    @Test
    public void getUser() {
        final Long testId = 1L;
        final Message message = userApiController.getUser(testId);
        verify(userService).getPlayer(testId);
    }

    @Test
    public void register() {
        final Message message = userApiController.register(null);
        verify(userService).registUser(null);
    }

    @Test
    public void authorize() {
        final Message message = userApiController.authorize(null, null);
        verify(userService).login(null, null);
    }

    @Test
    public void editProfile() {
        final Message message = userApiController.editProfile(null, null);
        verify(userService).editUser(null, null);
    }
}