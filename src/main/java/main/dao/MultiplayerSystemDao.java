package main.dao;

import main.domain.Multiplayer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MultiplayerSystemDao implements MultiplayerDao {

    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    public MultiplayerSystemDao(JdbcTemplate template, NamedParameterJdbcTemplate namedTemplate) {
        this.template = template;
        this.namedTemplate = namedTemplate;
    }

    @Override
    public void save(Multiplayer game) {

    }

    @Override
    public MultiplayerDao getById(Long id) {
        return null;
    }

    @Override
    public List<MultiplayerDao> findAll() {
        return null;
    }
}
