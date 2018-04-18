package main.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoryDaoSystem implements HistoryDao {

    private final JdbcTemplate template;

    public HistoryDaoSystem(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void getUserHistorySingleplayer(Long id) {

    }

    @Override
    public void getUserHistoryMultiplayer(Long id) {
    }
}
