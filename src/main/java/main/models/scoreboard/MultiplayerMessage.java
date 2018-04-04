package main.models.scoreboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.dao.UserDao;
import main.domain.Multiplayer;
import main.domain.User;

public class MultiplayerMessage {

    private UserDao userDao;

    private Long index;
    private Long score;

    @JsonProperty(value = "nickname1")
    private String nicknameFirst;

    @JsonProperty(value = "nickname2")
    private String getNicknameSecond;

    public MultiplayerMessage(UserDao userDao, Long index, Long score, String nicknameFirst, String getNicknameSecond) {
        this.userDao = userDao;
        this.index = index;
        this.score = score;
        this.nicknameFirst = nicknameFirst;
        this.getNicknameSecond = getNicknameSecond;
    }

    public MultiplayerMessage(Multiplayer multiplayer) {
        this.index = multiplayer.getId();
        this.score = multiplayer.getScore();
        final User player1 = userDao.getById(multiplayer.getUserFirstId());
        final User player2 = userDao.getById(multiplayer.getUserSecondId());
        this.nicknameFirst = player1.getNickname();
        this.getNicknameSecond = player2.getNickname();
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

    public String getNicknameFirst() {
        return nicknameFirst;
    }

    public void setNicknameFirst(String nicknameFirst) {
        this.nicknameFirst = nicknameFirst;
    }

    public String getGetNicknameSecond() {
        return getNicknameSecond;
    }

    public void setGetNicknameSecond(String getNicknameSecond) {
        this.getNicknameSecond = getNicknameSecond;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
