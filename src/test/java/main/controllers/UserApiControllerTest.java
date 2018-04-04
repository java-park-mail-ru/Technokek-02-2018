package main.controllers;

import main.Main;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.core.Is.is;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
@Transactional
public class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static Faker faker;
    private static String email;
    private static String nickname;
    private static String password;

    @BeforeAll
    public static void setUpFaker() {
        faker = new Faker(new Locale("en-US"));
    }

    @BeforeAll
    public static void setUpValues() {
        email = faker.internet().emailAddress();
        nickname = faker.name().username();
        password = faker.internet().password();
    }

    public void singup() throws Exception {
        mockMvc.perform(
                post("/signup")
                        .contentType("application/json")
                        .content("{\"email\":\"" + email + "\"," +
                                "\"nickname\":\"" + nickname + "\"," +
                                "\"avatar\":\"" + null + "\"," +
                                "\"score\":\"" + 0 + "\"," +
                                "\"games_number\":\"" + 0 + "\"," +
                                "\"password\":\"" + password + "\"}"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isCreated());
    }

    @Test
    public void signInOk() throws Exception {
        mockMvc.perform(
                post("/login")
                        .contentType("application/json")
                        .content("{\"email\":\"" + email + "\"," +
                                "\"password\":\"" + password + "\"}"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isCreated());
    }


}
