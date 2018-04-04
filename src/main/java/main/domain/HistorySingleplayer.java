package main.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "history_multiplayer")
public class HistorySingleplayer {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "game_id")
    private Long gameId;

    private Date date;

    public HistorySingleplayer(Long id, Long userId, Long gameId, Date date) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final HistorySingleplayer history = (HistorySingleplayer) o;
        return Objects.equals(id, history.id) &&
                Objects.equals(userId, history.userId) &&
                Objects.equals(gameId, history.gameId) &&
                Objects.equals(date, history.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, gameId, date);
    }
}
