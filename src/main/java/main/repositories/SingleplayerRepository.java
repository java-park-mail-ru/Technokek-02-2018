package main.repositories;

import main.domain.Singleplayer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SingleplayerRepository extends JpaRepository<Singleplayer, Long> {

}
