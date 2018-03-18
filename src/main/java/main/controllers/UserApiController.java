package main.controllers;

import main.models.User;
import main.models.Message;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/logout", produces = "application/json")
    public Message logout(HttpSession session) {
        return UserService.loguot(session);
    }

    @GetMapping(value = "/user/me", produces = "application/json")
    public Message getUser(HttpSession session) {
        return userService.getUserData(session);
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public Message getUser(@PathVariable("id") Long id) {
        return UserService.getPlayer(id);
    }

    @PostMapping(value = "/register", produces = "application/json")
    public Message register(@RequestBody User newbie) {
        return userService.registUser(newbie);
    }

    @GetMapping(value = "/{unknown}", produces = "application/json")
    public Message fail(@PathVariable("unknown") String unknown) {
        return UserService.notFound(unknown);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public Message authorize(@RequestBody User user, HttpSession session) {
        return userService.login(user, session);
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public Message editProfile(@RequestBody User user, HttpSession session) {
        return UserService.editUser(user, session);
    }

}
