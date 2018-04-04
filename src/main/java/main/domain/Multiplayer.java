package main.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "multiplayer")
public class Multiplayer {
    @Id
    @Column(name = "game_id")
    private Long id;


    @Column(name = "user_first_id")
    private Long userFirstId;


    @Column(name = "user_second_id")
    private Long userSecondId;

    @Column(name = "score")
    private Long score;

    public Multiplayer(Long id, Long userFirstId, Long userSecondId, Long score) {
        this.id = id;
        this.userFirstId = userFirstId;
        this.userSecondId = userSecondId;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserFirstId() {
        return userFirstId;
    }

    public void setUserFirstId(Long userFirstId) {
        this.userFirstId = userFirstId;
    }

    public Long getUserSecondId() {
        return userSecondId;
    }

    public void setUserSecondId(Long userSecondId) {
        this.userSecondId = userSecondId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Multiplayer game = (Multiplayer) object;
        return Objects.equals(id, game.id)
                &&
                Objects.equals(userFirstId, game.userFirstId)
                &&
                Objects.equals(userSecondId, game.userSecondId)
                &&
                Objects.equals(score, game.score);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userFirstId, userSecondId, score);
    }
}
