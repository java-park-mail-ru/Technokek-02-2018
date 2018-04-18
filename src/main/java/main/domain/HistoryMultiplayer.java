package main.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "history_singleplayer")
public class HistoryMultiplayer {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "game_id")
    private Long gameId;

    private Date date;

    public HistoryMultiplayer(Long id, Long userId, Long gameId, Date date) {
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final HistoryMultiplayer history = (HistoryMultiplayer) object;
        return Objects.equals(id, history.id)
                &&
                Objects.equals(userId, history.userId)
                &&
                Objects.equals(gameId, history.gameId)
                &&
                Objects.equals(date, history.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, gameId, date);
    }
}
