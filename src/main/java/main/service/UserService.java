package main.service;

import main.dao.*;
import main.domain.*;
import main.models.Error.ErrorStackMessages;
import main.models.Error.ErrorTypes;
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
                    errorStackMessages.addFieldError("email", ErrorTypes.ERRORS_MAP.get(ErrorTypes.USER_ALREADY_EXISTS));
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
        errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.INCORRECT_EMAIL_OR_PASSWORD));
        return new Message<>(false, errorStackMessages);
    }

    public Message getUserData(HttpSession session) {
        final Long id = (Long) session.getAttribute("userId");
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        if (id == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }

        final User curUser = userDao.getById(id);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }
        return new Message<>(true, new PlayerMessage(curUser));
    }

    public Message getScoreBoardSingleplayer(HttpSession session, Integer page) {

        final Long myId = (Long) session.getAttribute("userId");
        final HashMap<String, List> scoreboard = new HashMap<>();

        final List<Singleplayer> allGames = singleplayerDao.findAll();



        if (myId == null || singleplayerDao.getById(myId) == null) {
            final List<SingleplayerMessage> gameMessages =  new ArrayList<>();
            for (Singleplayer game : allGames) {
                gameMessages.add(new SingleplayerMessage(userDao, game));
            }


            scoreboard.put("me", null);
            scoreboard.put("another", singplayMes.paginate(page, gameMessages));

            System.out.println(gameMessages);

            return new Message<>(true, scoreboard);
        }

        final List<SingleplayerMessage> another = new ArrayList<>();
        final Singleplayer curUserGame = singleplayerDao.getById(myId);
        for (Singleplayer game : allGames) {
            if (!game.equals(curUserGame)) {
                another.add(new SingleplayerMessage(userDao, game));
            }
        }
        final List<SingleplayerMessage> me = new ArrayList<>();
        me.add(new SingleplayerMessage(userDao, curUserGame));
        scoreboard.put("me", me);
        scoreboard.put("another", singplayMes.paginate(page, another));
        return new Message<HashMap>(true, scoreboard);

    }

    public Message getScoreBoardMultiplayer(HttpSession session, Integer page) {

        final Long myId = (Long) session.getAttribute("userId");
        final HashMap<String, List> scoreboard = new HashMap<>();

        final List<Multiplayer> allGames = multiplayerDao.findAll();

        if (myId == null || multiplayerDao.getById(myId) == null) {
            final List<MultiplayerMessage> gameMessages =  new ArrayList<>();
            for (Multiplayer game : allGames) {
                gameMessages.add(new MultiplayerMessage(userDao, game));
            }

            scoreboard.put("me", null);
            scoreboard.put("another", multMes.paginate(page, gameMessages));
            return new Message<HashMap>(true, scoreboard);
        }

        final List<MultiplayerMessage> another = new ArrayList<>();
        final Multiplayer curUserGame = multiplayerDao.getById(myId);
        for (Multiplayer game : allGames) {
            if (!game.equals(curUserGame)) {
                another.add(new MultiplayerMessage(userDao, game));
            }
        }
        final List<MultiplayerMessage> me = new ArrayList<>();
        me.add(new MultiplayerMessage(userDao, curUserGame));
        scoreboard.put("me", me);
        scoreboard.put("another", multMes.paginate(page, another));
        return new Message<HashMap>(true, scoreboard);
    }

    public Message getHistorySingleplayer(HttpSession session, Integer page) {
        final Long myId = (Long) session.getAttribute("userId");
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        if (myId == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }

        final User curUser = userDao.getById(myId);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }
        final List<HistorySingleplayer> historyDb = historyDaoSystem.getUserHistorySingleplayer(myId);
        final List<HistorySingleplayerMessage> history = new ArrayList<>();
        for (HistorySingleplayer game : historyDb) {
            history.add(new HistorySingleplayerMessage(singleplayerDao, game));
        }
        return new Message<List>(true, histSingMes.paginate(page, history));
    }

    public Message getHistoryMultiplayer(HttpSession session, Integer page) {
        final Long myId = (Long) session.getAttribute("userId");
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        if (myId == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }

        final User curUser = userDao.getById(myId);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }
        final List<HistoryMultiplayer> historyDb = historyDaoSystem.getUserHistoryMultiplayer(myId);
        final List<HistoryMultiplayerMessage> history = new ArrayList<>();
        for (HistoryMultiplayer game : historyDb) {
            history.add(new HistoryMultiplayerMessage(userDao, multiplayerDao, game));
        }
        return new Message<List>(true, histMultMes.paginate(page, history));
    }

    public Message getPlayer(Long id) {
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        if (id == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_FOUND));
            return new Message<>(false, errorStackMessages);
        }
        final User curUser = userDao.getById(id);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_FOUND));
            return new Message<>(false, errorStackMessages);
        }
        return new Message<PlayerMessage>(true, new PlayerMessage(curUser));
    }

    public Message editUser(User user, HttpSession session) {
        final Long id = (Long) session.getAttribute("userId");
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        if (id == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }
        final User curUser = userDao.getById(id);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }
        userDao.update(user);
        return getUserData(session);
    }

    public Message loguot(HttpSession session) {
        ErrorStackMessages errorStackMessages = new ErrorStackMessages();
        final Long id = (Long) session.getAttribute("userId");
        if (id == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }
        final User curUser = userDao.getById(id);
        if (curUser == null) {
            errorStackMessages.addGlobalError(ErrorTypes.ERRORS_MAP.get(ErrorTypes.NOT_AUTHORIZED));
            return new Message<>(false, errorStackMessages);
        }
        session.invalidate();
        return new Message<>(true);
    }

}
