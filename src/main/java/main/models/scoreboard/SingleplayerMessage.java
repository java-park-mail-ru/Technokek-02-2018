package main.models.scoreboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.dao.UserDao;
import main.domain.Singleplayer;
import main.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleplayerMessage {

    @JsonIgnore
    private final UserDao userDao;

    private Long index;
    private Long score;
    private String nickname;

    @Autowired
    public SingleplayerMessage(UserDao userDao) {
        this.userDao = userDao;
    }

    public SingleplayerMessage(UserDao userDao, Singleplayer singleplayer) {
        this.userDao = userDao;
        this.index = singleplayer.getId();
        this.score = singleplayer.getScore();
        final User player = this.userDao.getById(singleplayer.getUserId());
        if (player != null) {
            this.nickname = player.getNickname();
        } else {
            this.nickname = null;
        }

        System.out.println("nickname = " + this.nickname);

    }


    public UserDao getUserDao() {
        return userDao;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
