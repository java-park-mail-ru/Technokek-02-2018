package main.service;

import main.dao.UserDao;
import main.models.Message;
import main.models.Player;
import main.models.User;
import main.service.viewService.ScoreBoardMapping;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

        @Autowired
        private UserDao userDao;

        public static Message notFound(String request) {
            final ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("404");
            arrayList.add("PAGE " + request + " NOT FOUND");
            return new Message<ArrayList>(true, arrayList);
        }

        public Message registUser(User newbie) {
            final List<User> allUsers = userDao.findAll();
            for (User user : allUsers) {
                if (user.equalEmail(newbie)) {
                    return new Message<String>(false, "USER_ALREADY_EXISTS");
                }
            }

            userDao.save(newbie);
            return new Message<String>(true, "USER_SUCCESSFULLY_REGISTERED");
        }

        public Message login(User checkUser, HttpSession session) {

            final List<User> userList = userDao.findAll();
            for (User cur : userList) {
                if (cur.equalEmailAndPassword(checkUser)) {
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
            return new Message<Player>(true, new Player(curUser));
        }

        public Message getScoreBoard(HttpSession session) {
            final Long id = (Long) session.getAttribute("userId");
            final User user = userDao.getById(id);
            final List<HashMap> players = new ArrayList<>();
            for (User curUser : userDao.findAll()) {
                if (!curUser.equals(user)) {
                    players.add(ScoreBoardMapping.getScoreInformation(curUser));
                }
            }
            final HashMap<String, List> scoreboard = new HashMap<>();
            scoreboard.put("another", players);
            scoreboard.put("me", ScoreBoardMapping.getMeInformation(user));
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
            return new Message<Player>(true, new Player(curUser));
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
            curUser.editUser(user.getEmail(), user.getLogin(), user.getPassword());
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
