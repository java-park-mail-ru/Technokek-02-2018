package main.controllers;

import main.models.Message;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class GameApiController {

    private final UserService userService;

    @Autowired
    public GameApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/scoreboard", produces = "application/json")
    public Message getScoreBoard(HttpSession session) {
        return UserService.getScoreBoard(session);
    }

    @GetMapping(value = "/about", produces = "application/json")
    public Message getAboutInf() {
        return UserService.about();
    }

    @GetMapping(value = "/getusers", produces = "application/json")
    public Message<List> getUsersInf() {
        return new Message<List>(true, userService.getUsersFromBD());
    }

}
