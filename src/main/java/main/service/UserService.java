package main.service;

import main.dao.*;
import main.domain.*;
import main.models.error.ErrorStackMessages;
import main.models.error.ErrorTypes;
import main.models.Message;
import main.models.PlayerMessage;
import main.models.history.HistoryMultiplayerMessage;
import main.models.history.HistorySingleplayerMessage;
import main.models.scoreboard.MultiplayerMessage;
import main.models.scoreboard.SingleplayerMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {


    private UserDao userDao;
    private SingleplayerSystemDao singleplayerDao;
    private MultiplayerSystemDao multiplayerDao;
    private HistoryDaoSystem historyDaoSystem;
    private Paginator<SingleplayerMessage> singplayMes;
    private Paginator<MultiplayerMessage> multMes;
    private Paginator<HistorySingleplayerMessage> histSingMes;
    private Paginator<HistoryMultiplayerMessage> histMultMes;

    public UserService(UserDao userDao, SingleplayerSystemDao singleplayerDao,
                       MultiplayerSystemDao multiplayerDao, HistoryDaoSystem historyDaoSystem) {
        this.userDao = userDao;
        this.singleplayerDao = singleplayerDao;
        this.multiplayerDao = multiplayerDao;
        this.historyDaoSystem = historyDaoSystem;
        this.singplayMes = new Paginator<>();
        this.multMes = new Paginator<>();
        this.histSingMes = new Paginator<>();
        this.histMultMes = new Paginator<>();
    }

    public Message registUser(User newbie, HttpSession httpSession) throws Exception {

            final List<User> allUsers = userDao.findAll();
            for (User user : allUsers) {
                if (user.getEmail().equals(newbie.getEmail())) {
                    ErrorStackMessages errorStackMessages = new ErrorStackMessages();
                    errorStackMessages.addFieldError("email", ErrorTypes.getErrorsMap().get(ErrorTypes.getUserAlreadyExists()));
                    return new Message<>(false, errorStackMessages);
                }
            }

            userDao.save(newbie);
            return login(newbie, httpSession);
        }

    public Message login(User checkUser, HttpSession session) {

        final List<User> userList = userDao.findAll();
        for (User cur : userList) {
            if (cur.getEmail().equals(checkUser.getEmail()) && cur.getPassword().equals(checkUser.getPassword())) {
                session.setAttribute("userId", cur.getId());
                return getUserData(session);
            }
        }
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getIncorrectEmailOrPassword()));
        return new Message<>(false, errorStackMessages);
    }

    public Message getUserData(HttpSession session) {
        final Long id = (Long) session.getAttribute("userId");
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        if (id == null) {
            errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getNotAuthorized()));
            return new Message<>(false, errorStackMessages);
        }

        final User curUser = userDao.getById(id);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getNotAuthorized()));
            return new Message<>(false, errorStackMessages);
        }
        return new Message<>(true, new PlayerMessage(curUser));
    }



    public Message getPlayer(Long id) {
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        if (id == null) {
            errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getNotFound()));
            return new Message<>(false, errorStackMessages);
        }
        final User curUser = userDao.getById(id);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getNotFound()));
            return new Message<>(false, errorStackMessages);
        }
        return new Message<PlayerMessage>(true, new PlayerMessage(curUser));
    }

    public Message editUser(User user, HttpSession session) {
        final Long id = (Long) session.getAttribute("userId");
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        if (id == null) {
            errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getNotAuthorized()));
            return new Message<>(false, errorStackMessages);
        }
        final User curUser = userDao.getById(id);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getNotAuthorized()));
            return new Message<>(false, errorStackMessages);
        }
        if (user.getEmail() != null) {
            curUser.setEmail(user.getEmail());
        }
        if (user.getNickname() != null) {
            curUser.setNickname(user.getNickname());
        }
        if (user.getPassword() != null) {
            curUser.setPassword(user.getPassword());
        }
        userDao.update(curUser);
        return getUserData(session);
    }

    public Message loguot(HttpSession session) {
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        final Long id = (Long) session.getAttribute("userId");
        if (id == null) {
            errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getNotAuthorized()));
            return new Message<>(false, errorStackMessages);
        }
        final User curUser = userDao.getById(id);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.getErrorsMap().get(ErrorTypes.getNotAuthorized()));
            return new Message<>(false, errorStackMessages);
        }
        session.invalidate();
        return new Message<>(true);
    }

}
