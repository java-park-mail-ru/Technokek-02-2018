package main.dao;

import main.domain.HistoryMultiplayer;
import main.domain.HistorySingleplayer;

import java.util.List;

public interface HistoryDao {

    List<HistorySingleplayer> getUserHistorySingleplayer(Long id);

    List<HistoryMultiplayer> getUserHistoryMultiplayer(Long id);

}
