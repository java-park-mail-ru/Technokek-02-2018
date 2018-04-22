package main.dao;

import main.models.history.HistorySingleplayerMessage;

import java.util.List;

public interface HistoryDao {

    List<HistorySingleplayerMessage> getUserHistorySingleplayer(Integer page, Long id);

    List<HistorySingleplayerMessage> getUserHistoryMultiplayer(Integer page, Long id);

}
