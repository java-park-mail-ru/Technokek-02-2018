package main.service;

import main.dao.HistoryDaoSystem;
import main.dao.MultiplayerSystemDao;
import main.dao.SingleplayerSystemDao;
import main.dao.UserDao;
import main.domain.Multiplayer;
import main.domain.Singleplayer;
import main.models.Message;
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
public class GameService {

    private UserDao userDao;
    private SingleplayerSystemDao singleplayerDao;
    private MultiplayerSystemDao multiplayerDao;
    private HistoryDaoSystem historyDaoSystem;
    private Paginator<SingleplayerMessage> singplayMes;
    private Paginator<MultiplayerMessage> multMes;
    private Paginator<HistorySingleplayerMessage> histSingMes;
    private Paginator<HistoryMultiplayerMessage> histMultMes;

    public GameService(UserDao userDao, SingleplayerSystemDao singleplayerDao, MultiplayerSystemDao multiplayerDao, HistoryDaoSystem historyDaoSystem, Paginator<SingleplayerMessage> singplayMes, Paginator<MultiplayerMessage> multMes, Paginator<HistorySingleplayerMessage> histSingMes, Paginator<HistoryMultiplayerMessage> histMultMes) {
        this.userDao = userDao;
        this.singleplayerDao = singleplayerDao;
        this.multiplayerDao = multiplayerDao;
        this.historyDaoSystem = historyDaoSystem;
        this.singplayMes = singplayMes;
        this.multMes = multMes;
        this.histSingMes = histSingMes;
        this.histMultMes = histMultMes;
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

    public void getHistorySingleplayer(HttpSession session, Integer page) {
    }

    public void getHistoryMultiplayer(HttpSession session, Integer page) {
    }
}
