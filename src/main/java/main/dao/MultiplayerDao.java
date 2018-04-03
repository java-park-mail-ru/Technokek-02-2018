package main.dao;

import main.domain.Multiplayer;

import java.util.List;

public interface MultiplayerDao {

    void save(Multiplayer game);

    MultiplayerDao getById(Long id);

    List<MultiplayerDao> findAll();

}
