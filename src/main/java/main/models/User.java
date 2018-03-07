package main.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicLong;

public class User {

    private Long id;

    @JsonProperty(value = "login")
    private String login;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

    public User() {
        this.id = ID_GENERATOR.getAndIncrement();
    }



    public User(String email, String login, String password) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.email = email;
        this.password = password;
        this.login = login;
    }

    public Boolean equalEmail(User user) {
        return user.email.equals(this.email);
    }

    public void editUser(String newEmail, String newLogin, String newPassword) {
        if (!StringUtils.isEmpty(newEmail)) {
            this.email = newEmail;
        }
        if (!StringUtils.isEmpty(newPassword)) {
            this.email = newPassword;
        }
        if (!StringUtils.isEmpty(newLogin)) {
            this.email = newLogin;
        }
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

}
