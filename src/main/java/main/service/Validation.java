package main.service;

import main.dao.UserDao;
import main.domain.User;
import main.models.Message;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class Validation {


    private UserDao userDao;

    public Validation(UserDao userDao) {
        this.userDao = userDao;
    }

    @Nullable
    public
    Message checkId(Long id) {
        if (id == null) {
            return new Message<String>(false, "NOT_LOGINED");
        }
        final User curUser = userDao.getById(id);
        if (curUser == null) {
            return new Message<String>(false, "INVALID_SESSION_ID");
        }
        return null;
    }
}
