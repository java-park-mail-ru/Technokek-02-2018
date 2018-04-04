package main.models.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.dao.MultiplayerSystemDao;
import main.dao.UserDao;
import main.domain.HistoryMultiplayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoryMultiplayerMessage {

    @JsonIgnore
    private UserDao userDao;

    @JsonIgnore
    private MultiplayerSystemDao multiplayerSystemDao;

    private Date date;
    private String partner;
    private Long score;

    @Autowired
    public HistoryMultiplayerMessage(UserDao userDao, MultiplayerSystemDao multiplayerSystemDao) {
        this.userDao = userDao;
        this.multiplayerSystemDao = multiplayerSystemDao;
    }

    public HistoryMultiplayerMessage(UserDao userDao, MultiplayerSystemDao multiplayerSystemDao, HistoryMultiplayer historyMultiplayer) {
        this.userDao = userDao;
        this.multiplayerSystemDao = multiplayerSystemDao;
        Long patnerId = this.multiplayerSystemDao.getById(historyMultiplayer.getGameId()).getUserFirstId();
        if (patnerId.equals(historyMultiplayer.getUserId())) {
            patnerId = this.multiplayerSystemDao.getById(historyMultiplayer.getGameId()).getUserSecondId();
        }
        this.partner = this.userDao.getById(patnerId).getNickname();
        this.date = historyMultiplayer.getDate();
        this.score = this.multiplayerSystemDao.getById(historyMultiplayer.getGameId()).getScore();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public MultiplayerSystemDao getMultiplayerSystemDao() {
        return multiplayerSystemDao;
    }

    public void setMultiplayerSystemDao(MultiplayerSystemDao multiplayerSystemDao) {
        this.multiplayerSystemDao = multiplayerSystemDao;
    }
}
