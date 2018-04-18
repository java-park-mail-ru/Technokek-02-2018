package main.models.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.dao.SingleplayerSystemDao;
import main.domain.HistorySingleplayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistorySingleplayerMessage {

    @JsonIgnore
    private final SingleplayerSystemDao singleplayerDao;

    private Date date;
    private Long score;

    @Autowired
    public HistorySingleplayerMessage(SingleplayerSystemDao singleplayerDao) {
        this.singleplayerDao = singleplayerDao;
    }

    public HistorySingleplayerMessage(SingleplayerSystemDao singleplayerDao, HistorySingleplayer historySingleplayer) {
        this.singleplayerDao = singleplayerDao;
        this.date = historySingleplayer.getDate();
        this.score = this.singleplayerDao.getById(historySingleplayer.getGameId()).getScore();
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
