package main.repositories;

import main.domain.Multiplayer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MultiplayerRepository extends JpaRepository<Multiplayer, Long> {

}
