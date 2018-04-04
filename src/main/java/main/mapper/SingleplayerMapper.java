package main.mapper;

import main.domain.HistoryMultiplayer;
import main.domain.HistorySingleplayer;
import main.domain.Singleplayer;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;

public class SingleplayerMapper {

    public static final RowMapper<Singleplayer> SINGLEPLAYER_MAPPER = (res, num) -> {
        Long id = res.getLong("game_id");
        Long score = res.getLong("score");
        Long userId = res.getLong("user_id");

        return new Singleplayer(id, userId, score);
    };

    public static final RowMapper<HistorySingleplayer> HISTORY_MAPPER = (res, num) -> {
        Long id = res.getLong("id");
        Long userId = res.getLong("user_id");
        Long gameId = res.getLong("game_id");
        Date date = res.getDate("date");

        return new HistorySingleplayer(id, userId, gameId, date);
    };
}
