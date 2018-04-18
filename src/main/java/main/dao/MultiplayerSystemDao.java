package main.dao;

import main.domain.Multiplayer;
import main.mapper.MultiplayerMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MultiplayerSystemDao implements MultiplayerDao {

    private final JdbcTemplate template;

    public MultiplayerSystemDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void save(Long userFirstId, Long userSecond, Long score) {
        try {
            final String sql = "INSERT INTO multiplayer (score, user_first_id, user_second_id) VALUES (?,?,?)";
            template.update(sql, score, userFirstId, userSecond);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Multiplayer getById(Long id) {
        final String sql = "SELECT * FROM multiplayer WHERE game_id = ?";
        final List<Multiplayer> result = template.query(sql, ps -> ps.setLong(1, id), MultiplayerMapper.MULTIPLAYER_MAPPER);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<Multiplayer> findAll() {
        return template.query("select * from multiplayer", MultiplayerMapper.MULTIPLAYER_MAPPER);
    }
}
