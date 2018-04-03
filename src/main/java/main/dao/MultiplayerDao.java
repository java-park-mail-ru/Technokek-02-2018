package main.dao;

import main.domain.Multiplayer;

import java.util.List;

public interface MultiplayerDao {

    void save(Long userFirstId, Long userSecond, Long score);

    Multiplayer getById(Long id);

    List<Multiplayer> findAll();

}
