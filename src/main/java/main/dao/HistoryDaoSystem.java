package main.dao;

import main.domain.HistoryMultiplayer;
import main.domain.HistorySingleplayer;
import main.mapper.MultiplayerMapper;
import main.mapper.SingleplayerMapper;
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
    public List<HistorySingleplayer> getUserHistorySingleplayer(Long id) {
        final String sql = "SELECT * FROM history_singleplayer WHERE user_id = ?";
        final List<HistorySingleplayer> result = template.query(sql, ps -> ps.setLong(1, id), SingleplayerMapper.HISTORY_MAPPER);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public List<HistoryMultiplayer> getUserHistoryMultiplayer(Long id) {
        final String sql = "SELECT * FROM history_multiplayer WHERE user_id = ?";
        final List<HistoryMultiplayer> result = template.query(sql, ps -> ps.setLong(1, id), MultiplayerMapper.HISTORY_MAPPER);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }
}
