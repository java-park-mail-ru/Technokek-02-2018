package main.dao;

import main.domain.Singleplayer;

import java.util.List;

public interface SingleplayerDao {

    void save(Singleplayer game);

    Singleplayer getById(Long id);

    List<Singleplayer> findAll();
}
