package main.controllers;

import main.models.Message;
import main.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class GameApiController {

    @GetMapping(value = "/scoreboard", produces = "application/json")
    public Message getScoreBoard(HttpSession session) {
        return UserService.getScoreBoard(session);
    }

    @GetMapping(value = "/about", produces = "application/json")
    public Message getAboutInf() {
        return UserService.about();
    }

}
