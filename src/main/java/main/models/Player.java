package main.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private Long id;

    @JsonProperty(value = "nickname")
    private String login;

    @JsonProperty(value = "score")
    private int score;

    public Player(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.score = 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }
}
