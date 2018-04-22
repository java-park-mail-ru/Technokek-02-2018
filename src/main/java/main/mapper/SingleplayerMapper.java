package main.mapper;

import main.domain.Singleplayer;
import main.models.history.HistorySingleplayerMessage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

public class SingleplayerMapper {

    public static final RowMapper<Singleplayer> SINGLEPLAYER_MAPPER = (res, num) -> {
        Long id = res.getLong("game_id");
        Long score = res.getLong("score");
        Long userId = res.getLong("user_id");

        return new Singleplayer(id, userId, score);
    };

    public static final RowMapper<HistorySingleplayerMessage> HISTORY_MAPPER = (res, num) -> {
        Long id = res.getLong("game_id");
        Long score = res.getLong("score");
        Long userId = res.getLong("user_id");
        Timestamp time = res.getTimestamp("date");

        return new HistorySingleplayerMessage(id,time, userId, score);
    };
}
