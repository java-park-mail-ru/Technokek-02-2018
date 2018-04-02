package main.dao;

import java.util.List;
import main.domain.Multiplayer;

public interface MultiplayerDao {

    void save(Multiplayer game);

    MultiplayerDao getById(Long id);

    List<MultiplayerDao> findAll();

}
