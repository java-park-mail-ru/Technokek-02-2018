package main.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
	@Id
	private Long id;
	private String nickname;
	private String email;
	private String password;
	private String avatar;

	public User(Long id, String nickname, String email, String password, String avatar, Integer gamesNumber) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.gamesNumber = gamesNumber;
	}

	@Column(name = "games_number")
	private Integer gamesNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getGamesNumber() {
		return gamesNumber;
	}

	public void setGamesNumber(Integer gamesNumber) {
		this.gamesNumber = gamesNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(nickname, user.nickname) &&
				Objects.equals(email, user.email) &&
				Objects.equals(password, user.password) &&
				Objects.equals(avatar, user.avatar) &&
				Objects.equals(gamesNumber, user.gamesNumber);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, nickname, email, password, avatar, gamesNumber);
	}

}
