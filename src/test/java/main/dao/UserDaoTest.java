package main.dao;

import main.dao.UserDaoSystem;
import main.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Autowired
    private UserDaoSystem userDaoSystem;

    private final User testUser = new User((long)1,"test", "e.mail@mail.ru", "test", "sdfsd",0,0);

    @Test
    public void signUpSuccess() throws Exception {
       userDaoSystem.save(testUser);
    }

    @Test
    public void signUpDuplicateEmail() throws Exception {
        userDaoSystem.save(testUser);
        userDaoSystem.save(testUser);
    }


    @Test
    public void loginCorrect() {
        userDaoSystem.save(testUser);
        final User user = userDaoSystem.getById(testUser.getId());

        assertEquals(testUser.getNickname(), user.getNickname());
        assertEquals(testUser.getPassword(), user.getPassword());
        assertEquals(testUser.getEmail(), user.getEmail());
    }

    @Test
    public void deleteUser() {
        userDaoSystem.save(testUser);
        userDaoSystem.delete(testUser.getId().intValue());
        final User user = userDaoSystem.getById(testUser.getId());
        assertEquals(user, null);
    }

    @Test
    public void userCorrectUpdate() {
        userDaoSystem.save(testUser);
        testUser.setNickname("newTest");
        userDaoSystem.update(testUser);
        final User user = userDaoSystem.getById(testUser.getId());
        assertEquals(user, testUser);
    }

    @Test
    public void findAllUserSuccess() {
        final List<User> usersList = userDaoSystem.findAll();
        assertTrue( !usersList.isEmpty());
    }

}
