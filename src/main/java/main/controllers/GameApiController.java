package main.controllers;

import main.models.Message;
import main.service.UserService;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


@RestController
public class GameApiController {

    private final UserService userService;

    public GameApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/scoreboard/singleplayer/{page}", produces = "application/json")
    public Message getScoreBoardSingle(@PathVariable("page") Integer page, HttpSession session) {
        return userService.getScoreBoardSingleplayer(session, page);
    }

    @GetMapping(value = "/history/singleplayer/{page}", produces = "application/json")
    public Message getHistorySingle(@PathVariable("page") Integer page, HttpSession session) {
        return userService.getHistorySingleplayer(session, page);
    }

    @GetMapping(value = "/scoreboard/multiplayer/{page}")
    public Message getScoreBoardMulti(@PathVariable("page") Integer page, HttpSession session) {
        return userService.getScoreBoardMultiplayer(session, page);
    }

    @GetMapping(value = "/history/multiplayer/{page}", produces = "application/json")
    public Message getHistoryMulti(@PathVariable("page") Integer page, HttpSession session) {
        return userService.getHistoryMultiplayer(session, page);
    }

    @GetMapping(value = "/about", produces = "application/json")
    public Message getAboutInf() throws IOException, ParseException, FileNotFoundException {

        final JSONParser parser = new JSONParser();
        final Object object = parser.parse(new FileReader("src/main/resources/rules_about/about.json"));
        final JSONArray jsonObject = (JSONArray) object;

        return new Message<>(true, jsonObject);
    }

    @GetMapping(value = "/rules", produces = "application/json")
    public Message getRulesInf() throws IOException, ParseException, FileNotFoundException {

        final JSONParser parser = new JSONParser();
        final Object object = parser.parse(new FileReader("src/main/resources/rules_about/rules.json"));
        final JSONArray jsonObject = (JSONArray) object;

        return new Message<>(true, jsonObject);
    }

}
