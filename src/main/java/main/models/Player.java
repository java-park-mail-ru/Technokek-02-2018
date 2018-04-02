package main.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private Long id;

    @JsonProperty(value = "nickname")
    private String nickname;

    @JsonProperty(value = "score")
    private int score;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "games_number")
    private int gamesNumber;

    @JsonProperty(value = "avatar")
    private String avatar;

    public Player(User user) {
        this.id = user.getId();
        this.nickname = user.getLogin();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.score = user.getScore();
        this.gamesNumber = user.getGamesNumber();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getScore() {
        return score;
    }

    public Long getId() {
        return id;
    }

    public int getGamesNumber() {
        return gamesNumber;
    }
}
