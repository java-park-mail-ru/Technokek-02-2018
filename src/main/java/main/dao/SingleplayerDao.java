package main.dao;

import java.util.List;
import main.domain.Singleplayer;

public interface SingleplayerDao {

    void save(Singleplayer game);

    Singleplayer getById(Long id);

    List<Singleplayer> findAll();
}
