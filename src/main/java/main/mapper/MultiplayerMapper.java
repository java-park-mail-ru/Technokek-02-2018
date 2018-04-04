package main.mapper;

import main.domain.HistoryMultiplayer;
import main.domain.Multiplayer;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

public class MultiplayerMapper {
    public static final RowMapper<Multiplayer> MULTIPLAYER_MAPPER = (res, num) -> {
        Long id = res.getLong("game_id");
        Long score = res.getLong("score");
        Long userIdFirst = res.getLong("user_first_id");
        Long userIdSecond = res.getLong("user_second_id");

        return new Multiplayer(id, userIdFirst, userIdSecond, score);
    };

    public static final RowMapper<HistoryMultiplayer> HISTORY_MAPPER = (res, num) -> {
        Long id = res.getLong("id");
        Long userId = res.getLong("user_id");
        Long gameId = res.getLong("game_id");
        Date date = res.getDate("date");

        return new HistoryMultiplayer(id, userId, gameId, date);
    };
}
