package main.dao;

import main.domain.User;
import main.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDaoSystem implements UserDao {

    private final JdbcTemplate template;

    public UserDaoSystem(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void save(User user) {
        final String sql = "INSERT INTO Users (nickname, email, password) VALUES (?,?,?)";
        template.update(sql, user.getNickname(), user.getEmail(), user.getPassword());
    }

    @Override
    public User getById(Long id) {
        final String sql = "SELECT * FROM Users WHERE id = ?";
        final List<User> result = template.query(sql, ps -> ps.setLong(1, id), UserMapper.USER_MAPPER);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<User> findAll() {
        return template.query("select * from Users", UserMapper.USER_MAPPER);
    }

    @Override
    public void update(User user) {
        final String sql = "UPDATE Users SET nickname=?, email=?, password=? WHERE id=?";
        template.update(sql, user.getNickname(), user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public void delete(int id) {
        final String sql = "DELETE FROM Users WHERE id=?";
        template.update(sql, id);
    }
}
