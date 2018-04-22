package main.models.history;


import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class HistoryMultiplayerMessage {

    private Long id;
    private Long score;
    private String partner;
    private Timestamp date;

    public HistoryMultiplayerMessage(Long id, Long score, String partner, Timestamp date) {
        this.id = id;
        this.score = score;
        this.partner = partner;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryMultiplayerMessage that = (HistoryMultiplayerMessage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(score, that.score) &&
                Objects.equals(partner, that.partner) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, score, partner, date);
    }
}
