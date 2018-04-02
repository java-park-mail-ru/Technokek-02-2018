package main.dao;

import main.mapper.UserMapper;
import main.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoSystem implements UserDao {


    @Override
    public void save(User user) {
        final String sql = "INSERT INTO Users (nickname, email, password) VALUES (?,?,?)";
        jdbcTemplate.update(sql, user.getLogin(), user.getEmail(), user.getPassword());
    }

    @Override
    public User getById(Long id) {
        final String sql = "SELECT * FROM Users WHERE id = ?";
        try {
            jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    @Override
    public void update(User user) {
        final String sql = "UPDATE Users SET nickname=?, email=?, password=? WHERE id=?";
        jdbcTemplate.update(sql, user.getLogin(), user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public void delete(int id) {
        final String sql = "DELETE FROM Users WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoSystem(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}

