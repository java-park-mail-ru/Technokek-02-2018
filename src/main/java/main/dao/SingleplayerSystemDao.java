package main.dao;

import main.domain.Singleplayer;
import main.mapper.SingleplayerMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SingleplayerSystemDao implements SingleplayerDao {

    private final JdbcTemplate template;

    public SingleplayerSystemDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void save(Long userId, Long score) {
        try {
            final String sql = "INSERT INTO Singleplayer (score, user_id) VALUES (?,?)";
            template.update(sql, userId, score);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Singleplayer getById(Long id) {
        final String sql = "SELECT * FROM singleplayer WHERE game_id = ?";
        final List<Singleplayer> result = template.query(sql, ps -> ps.setLong(1, id), SingleplayerMapper.SINGLEPLAYER_MAPPER);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<Singleplayer> findAll() {
        return template.query("select * from singleplayer", SingleplayerMapper.SINGLEPLAYER_MAPPER);
    }
}
