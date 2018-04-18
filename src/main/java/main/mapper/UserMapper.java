package main.mapper;

import main.domain.User;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper {
    public static final RowMapper<User> USER_MAPPER = (res, num) -> {
        Long id = res.getLong("id");
        String nickname = res.getString("nickname");
        String email = res.getString("email");
        String password = res.getString("password");
        Integer score = res.getInt("score");
        Integer gamesNumber = res.getInt("games_number");
        String avatar = res.getString("avatar");

        return new User(id, nickname, email, password, avatar, score, gamesNumber);
    };
}
