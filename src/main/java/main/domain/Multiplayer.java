package main.domain;

import javax.persistence.*;

@Entity
@Table(name = "multiplayer")
public class Multiplayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;

    @OneToOne(mappedBy = "user")
    @Column(name = "user_first_id")
    private Long userFirstId;

    @OneToOne(mappedBy = "user")
    @Column(name = "user_second_id")
    private Long userSecondId;

    @Column(name = "score")
    private Long score;

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
}
