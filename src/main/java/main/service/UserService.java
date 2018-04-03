package main.service;

import main.dao.MultiplayerDao;
import main.dao.SingleplayerDao;
import main.dao.UserDao;
import main.domain.Multiplayer;
import main.domain.Singleplayer;
import main.domain.User;
import main.models.Message;
import main.models.PlayerMessage;
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
        private SingleplayerDao singleplayerDao;
        private MultiplayerDao multiplayerDao;

        public UserService(UserDao userDao, SingleplayerDao singleplayerDao, MultiplayerDao multiplayerDao) {
            this.userDao = userDao;
            this.singleplayerDao = singleplayerDao;
            this.multiplayerDao = multiplayerDao;
        }


        public Message registUser(User newbie) {
            final List<User> allUsers = userDao.findAll();
            for (User user : allUsers) {
                if (user.getEmail().equals(newbie.getEmail())) {
                    return new Message<String>(false, "USER_ALREADY_EXISTS");
                }
            }

            userDao.save(newbie);
            return new Message<String>(true, "USER_SUCCESSFULLY_REGISTERED");
        }

        public Message login(User checkUser, HttpSession session) {

            final List<User> userList = userDao.findAll();
            for (User cur : userList) {
                if (cur.getEmail().equals(checkUser.getEmail()) && cur.getPassword().equals(checkUser.getPassword())) {
                    session.setAttribute("userId", cur.getId());
                    return new Message<String>(true, "USER_SUCCESSFULLY_LOGIN");
                }
            }
            return new Message<String>(false, "WRONG_DATA_FOR_LOGIN");
        }

        public Message getUserData(HttpSession session) {
            final Long id = (Long) session.getAttribute("userId");
            if (id == null) {
                return new Message<String>(false, "NOT_LOGINED");
            }

            final User curUser = userDao.getById(id);
            if (curUser == null) {
                return new Message<String>(false, "INVALID_SESSION_ID");
            }
            return new Message<User>(true, curUser);
        }

        public Message getScoreBoardSingleplayer(HttpSession session, Long page) {

            final Long myId = (Long) session.getAttribute("userId");
            final HashMap<String, List> scoreboard = new HashMap<>();

            final List<Singleplayer> allGames = singleplayerDao.findAll();

            if (myId == null || singleplayerDao.getById(myId) == null ) {
                final List<SingleplayerMessage> gameMessages =  new ArrayList<>();
                for (Singleplayer game : allGames) {
                    gameMessages.add(new SingleplayerMessage(game));
                }

                scoreboard.put("me", null);
                scoreboard.put("anoyher", gameMessages);
                return new Message<HashMap>(true, scoreboard);
            }

            final List<SingleplayerMessage> another = new ArrayList<>();
            final Singleplayer curUserGame = singleplayerDao.getById(myId);
            for (Singleplayer game : allGames) {
                if (!game.equals(curUserGame)) {
                    another.add(new SingleplayerMessage(game));
                }
            }
            final List<SingleplayerMessage> me = new ArrayList<>();
            me.add(new SingleplayerMessage(curUserGame));
            scoreboard.put("me", me);
            scoreboard.put("another", another);
            return new Message<HashMap>(true, scoreboard);

        }

        public Message getScoreBoardMultiplayer(HttpSession session, Long page) {

            final Long myId = (Long) session.getAttribute("userId");
            final HashMap<String, List> scoreboard = new HashMap<>();

            final List<Multiplayer> allGames = multiplayerDao.findAll();

            if (myId == null || multiplayerDao.getById(myId) == null ) {
                final List<MultiplayerMessage> gameMessages =  new ArrayList<>();
                for (Multiplayer game : allGames) {
                    gameMessages.add(new MultiplayerMessage(game));
                }

                scoreboard.put("me", null);
                scoreboard.put("anoyher", gameMessages);
                return new Message<HashMap>(true, scoreboard);
            }

            final List<MultiplayerMessage> another = new ArrayList<>();
            final Multiplayer curUserGame = multiplayerDao.getById(myId);
            for (Multiplayer game : allGames) {
                if (!game.equals(curUserGame)) {
                    another.add(new MultiplayerMessage(game));
                }
            }
            final List<MultiplayerMessage> me = new ArrayList<>();
            me.add(new MultiplayerMessage(curUserGame));
            scoreboard.put("me", me);
            scoreboard.put("another", another);
            return new Message<HashMap>(true, scoreboard);
        }

        public Message getPlayer(Long id) {
            if (id == null) {
                return new Message<String>(false, "NOT_LOGINED");
            }
            final User curUser = userDao.getById(id);
            if (curUser == null) {
                return new Message<String>(false, "INVALID_SESSION_ID");
            }
            return new Message<PlayerMessage>(true, new PlayerMessage(curUser));
        }

        public Message editUser(User user, HttpSession session) {
            final Long id = (Long) session.getAttribute("userId");
            if (id == null) {
                return new Message<String>(false, "NOT_LOGINED");
            }
            final User curUser = userDao.getById(id);
            if (curUser == null) {
                return new Message<String>(false, "INVALID_SESSION_ID");
            }
            userDao.update(user);
            return new Message<String>(true, "USER_SUCCESSFULLY_CHANGED");
        }

        public Message loguot(HttpSession session) {
            final Long id = (Long) session.getAttribute("userId");
            if (id == null) {
                return new Message<String>(false, "NOT_LOGINED");
            }
            final User curUser = userDao.getById(id);
            if (curUser == null) {
                return new Message<String>(false, "INVALID_SESSION_ID");
            }
            session.invalidate();
            return new Message<String>(true, "USER_SUCCESSFULLY_UNLOGIN");
        }

        public static Message about() {
            return new Message<String>(true, "Hello, world!");
        }


        public List<User> getUsersFromBD() {
            return userDao.findAll();
        }
        
}
