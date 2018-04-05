package main.controllers;

import main.domain.User;
import main.models.Message;
import main.service.UserService;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/logout", produces = "application/json")
    public Message logout(HttpSession session) {
        return userService.loguot(session);
    }

    @GetMapping(value = "/user/me", produces = "application/json")
    public Message getMe(HttpSession session) {
        return userService.getUserData(session);
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public Message getUser(@PathVariable("id") Long id) {
        return userService.getPlayer(id);
    }

    @PostMapping(value = "/signup", produces = "application/json")
    public Message register(@RequestBody User newbie, HttpSession httpSession) throws Exception {
        return userService.registUser(newbie, httpSession);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public Message authorize(@RequestBody User user, HttpSession session) {
        return userService.login(user, session);
    }

    @PostMapping(value = "/user/edit", produces = "application/json")
    public Message editProfile(@RequestBody User user, HttpSession session) {
        return userService.editUser(user, session);
    }

}
