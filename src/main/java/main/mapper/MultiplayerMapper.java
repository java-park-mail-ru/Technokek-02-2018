package main.mapper;

import main.domain.Multiplayer;
import org.springframework.jdbc.core.RowMapper;

public class MultiplayerMapper {
    public static final RowMapper<Multiplayer> MULTIPLAYER_MAPPER = (res, num) -> {
        Long id = res.getLong("game_id");
        Long score = res.getLong("score");
        Long userIdFirst = res.getLong("user_first_id");
        Long userIdSecond = res.getLong("user_second_id");

        return new Multiplayer(id, userIdFirst, userIdSecond, score);
    };
}
