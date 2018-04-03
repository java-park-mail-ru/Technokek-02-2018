package main.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicLong;

public class UserMessage {

    private Long id;

    @JsonProperty(value = "login")
    private String login;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "avatar")
    private String avatar;

    @JsonProperty(value = "score")
    private Integer score;

    @JsonProperty(value = "games_number")
    private Integer gamesNumber;

    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

    public UserMessage() {
        this.id = ID_GENERATOR.getAndIncrement();
    }



    public UserMessage(String email, String login, String password) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.email = email;
        this.password = password;
        this.login = login;
    }

    public UserMessage(String email, String login, String password, String avatar) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.email = email;
        this.password = password;
        this.login = login;
        this.avatar = avatar;
    }

    public Boolean equalEmail(UserMessage user) {
        return user.email.equals(this.email);
    }

    public Boolean equalEmailAndPassword(UserMessage user) {
        return user.email.equals(this.email) && user.password.equals(this.password);
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

    public Integer getGamesNumber() {
        return gamesNumber;
    }

    public void setGamesNumber(Integer gamesNumber) {
        this.gamesNumber = gamesNumber;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
