package main.models.scoreboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import main.dao.UserDao;
import main.domain.Multiplayer;
import main.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiplayerMessage {

    @JsonIgnore
    private final UserDao userDao;

    private Long index;
    private Long score;

    @JsonProperty(value = "nickname1")
    private String nicknameFirst;

    @JsonProperty(value = "nickname2")
    private String getNicknameSecond;

    @Autowired
    public MultiplayerMessage(UserDao userDao) {
        this.userDao = userDao;
    }

    public MultiplayerMessage(UserDao userDao, Multiplayer multiplayer) {
        this.userDao = userDao;
        this.index = multiplayer.getId();
        this.score = multiplayer.getScore();
        final User player1 = this.userDao.getById(multiplayer.getUserFirstId());
        final User player2 = this.userDao.getById(multiplayer.getUserSecondId());
        if (player1 != null) {
            this.nicknameFirst = player1.getNickname();
        } else {
            this.nicknameFirst = null;
        }
        if (player2 != null) {
            this.getNicknameSecond = player2.getNickname();
        } else {
            this.getNicknameSecond = null;
        }
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
}
