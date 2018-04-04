package main.models.history;

import main.dao.MultiplayerSystemDao;
import main.dao.UserDao;
import main.domain.HistoryMultiplayer;

import java.util.Date;

public class HistoryMultiplayerMessage {

    private UserDao userDao;
    private MultiplayerSystemDao multiplayerSystemDao;

    private Date date;
    private String partner;
    private Long score;

    public HistoryMultiplayerMessage(UserDao userDao, MultiplayerSystemDao multiplayerSystemDao, Date date, String partner, Long score) {
        this.userDao = userDao;
        this.multiplayerSystemDao = multiplayerSystemDao;
        this.date = date;
        this.partner = partner;
        this.score = score;
    }

    public HistoryMultiplayerMessage(HistoryMultiplayer historyMultiplayer) {
        Long patnerId = multiplayerSystemDao.getById(historyMultiplayer.getGameId()).getUserFirstId();
        if (patnerId.equals(historyMultiplayer.getUserId())) {
            patnerId = multiplayerSystemDao.getById(historyMultiplayer.getGameId()).getUserSecondId();
        }
        this.partner = userDao.getById(patnerId).getNickname();
        this.date = historyMultiplayer.getDate();
        this.score = multiplayerSystemDao.getById(historyMultiplayer.getGameId()).getScore();
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


}
