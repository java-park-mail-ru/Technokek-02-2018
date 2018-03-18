package main.dao;

import main.models.User;
import java.util.List;

public interface UserDao {

        void save(User user);

        User getById(Long id);

        List<User> findAll();

        void update(User user);

        void delete(int id);

}
