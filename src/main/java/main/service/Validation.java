package main.service;

import main.dao.UserDao;
import main.models.Message;
import main.models.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Validation {

    @Autowired
    private UserDao userDao;

    public @Nullable Message checkId(Long id) {
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
