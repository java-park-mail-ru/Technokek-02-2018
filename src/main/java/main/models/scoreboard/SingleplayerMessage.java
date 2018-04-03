package main.models.scoreboard;

import main.dao.UserDao;
import main.domain.Singleplayer;
import main.domain.User;

public class SingleplayerMessage {

    private UserDao userDao;

    private Long index;
    private Long score;
    private String nickname;

    public SingleplayerMessage(UserDao userDao, Long index, Long score, String nickname) {
        this.userDao = userDao;
        this.index = index;
        this.score = score;
        this.nickname = nickname;
    }

    public SingleplayerMessage(Singleplayer singleplayer) {
        this.index = singleplayer.getId();
        this.score = singleplayer.getScore();
        final User player = userDao.getById(singleplayer.getUserId());
        this.nickname = player.getNickname();
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }
}
