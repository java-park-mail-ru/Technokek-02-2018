package main.dao;

import main.domain.Singleplayer;

import java.util.List;

public interface SingleplayerDao {

    void save(Long userId, Long score);

    Singleplayer getById(Long id);

    List<Singleplayer> findAll();
}
