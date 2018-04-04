package main.dao;

import main.domain.User;

import java.util.List;

public interface UserDao {

    void save(User user) throws Exception;

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