package main.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sigleplayer")
public class Singleplayer {
	@Id
	@Column(name = "game_id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "score")
	private Long score;

	public Singleplayer(Long id, Long userId, Long score) {
		this.id = id;
		this.userId = userId;
		this.score = score;
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

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Singleplayer game = (Singleplayer) o;
		return Objects.equals(id, game.id) &&
				Objects.equals(userId, game.userId) &&
				Objects.equals(score, game.score);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, userId, score);
	}
}
