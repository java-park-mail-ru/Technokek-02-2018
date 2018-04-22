package main.dao;

import main.domain.Multiplayer;
import main.mapper.MultiplayerMapper;
import main.mapper.SingleplayerMapper;
import main.models.history.HistorySingleplayerMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HistoryDaoSystem implements HistoryDao {

    private final JdbcTemplate template;

    public HistoryDaoSystem(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<HistorySingleplayerMessage> getUserHistorySingleplayer(Integer page, Long id) {
        if (page < 1) {
            return null;
        }

        final String sql = "SELECT * FROM singleplayer WHERE user_id = ? ORDER BY date LIMIT 10 OFFSET ? ";
        final List<HistorySingleplayerMessage> result = template.query(sql, ps -> {
            ps.setInt(1, (page-1)*10 );
            ps.setLong(2, id);
            }, SingleplayerMapper.HISTORY_MAPPER);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public List<HistorySingleplayerMessage> getUserHistoryMultiplayer(Integer page, Long id) {
        if (page < 1) {
            return null;
        }

        final String sql = "SELECT * FROM multiplayer WHERE user_first_id = ? OR user_second_id = ? ORDER BY date LIMIT 10 OFFSET ? ";
        final List<HistorySingleplayerMessage> result = template.query(sql, ps -> {
            ps.setLong(1, id);
            ps.setLong(2, id);
            ps.setInt(3, (page-1)*10 );
        }, SingleplayerMapper.HISTORY_MAPPER);
        if (result.isEmpty()) {
            return null;
        }

        return result;
    }
}
