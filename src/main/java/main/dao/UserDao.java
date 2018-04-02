package main.dao;

import java.util.List;
import main.domain.User;

public interface UserDao {

    void save(User user);

    User getById(Long id);

    List<User> findAll();

    void update(User user);

    void delete(int id);

    class DuplicateUserException extends RuntimeException {
        public DuplicateUserException(String name, Throwable cause) {
            super("User with name " + name + " already exists", cause);
        }
    }
}