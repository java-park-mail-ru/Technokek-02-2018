package main.models.history;

import main.dao.SingleplayerDao;
import main.dao.SingleplayerSystemDao;
import main.domain.HistorySingleplayer;

import java.util.Date;

public class HistorySingleplayerMessage {

    private SingleplayerSystemDao singleplayerDao;

    private Date date;
    private Long score;

    public HistorySingleplayerMessage(SingleplayerSystemDao singleplayerDao, Date date, Long score) {
        this.singleplayerDao = singleplayerDao;
        this.date = date;
        this.score = score;
    }

    public HistorySingleplayerMessage(HistorySingleplayer historySingleplayer) {
        this.date = historySingleplayer.getDate();
        this.score = singleplayerDao.getById(historySingleplayer.getGameId()).getScore();
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
