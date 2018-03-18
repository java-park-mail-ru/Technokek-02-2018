package main.service;

import main.dao.UserDao;
import main.data.UserList;
import main.models.Message;
import main.models.Player;
import main.models.User;
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
            if (!UserList.uniqueUser(newbie.getEmail())) {
                    return new Message<String>(false, "USER_ALREADY_EXISTS");
            }

            // UserList.addUser(newbie);

            userDao.save(newbie);
            return new Message<String>(true, "USER_SUCCESSFULLY_REGISTERED");
        }

        public Message login(User checkUser, HttpSession session) {

            /*if (UserList.login(email, password)) {
                    session.setAttribute("userId", UserList.getId(email));
                    return new Message<String>(true, "USER_SUCCESSFULLY_LOGIN");
            }
            return new Message<String>(false, "WRONG_DATA_FOR_LOGIN");*/

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

            // final User curUser = UserList.getById(id);

            final User curUser = userDao.getById(id);
            if (curUser == null) {
                return new Message<String>(false, "INVALID_SESSION_ID");
            }
            return new Message<User>(true, curUser);
        }

        public static Message getScoreBoard(HttpSession session) {
            final Long id = (Long) session.getAttribute("userId");
            final User curUser = UserList.getById(id);
            final HashMap<String, ArrayList> scoreList = new HashMap<>();
            if (Validation.checkId(id) == null) {
                assert curUser != null;
                final ArrayList<Player> curUserScore = new ArrayList<>();
                curUserScore.add(new Player(curUser));
                scoreList.put("me", curUserScore);
                final ArrayList<Player> usersScore = UserList.toArrayListPlayersWithoutId(id);
                scoreList.put("another", usersScore);
                return new Message<HashMap>(true, scoreList);
            }
            scoreList.put("me", null);
            scoreList.put("another", UserList.toArrayListPlayersWithoutId(null));
            return new Message<HashMap>(true, scoreList);
        }

        public static Message getPlayer(Long id) {
            if (id == null) {
                return new Message<String>(false, "NOT_LOGINED");
            }
            final User curUser = UserList.getById(id);
            if (curUser == null) {
                return new Message<String>(false, "INVALID_SESSION_ID");
            }
            return new Message<Player>(true, new Player(curUser));
        }

        public static Message editUser(User user, HttpSession session) {
            final Long id = (Long) session.getAttribute("userId");
            if (id == null) {
                return new Message<String>(false, "NOT_LOGINED");
            }
            final User curUser = UserList.getById(id);
            if (curUser == null) {
                return new Message<String>(false, "INVALID_SESSION_ID");
            }
            curUser.editUser(user.getEmail(), user.getLogin(), user.getPassword());
            return new Message<String>(true, "USER_SUCCESSFULLY_CHANGED");
        }

        public static Message loguot(HttpSession session) {
            final Long id = (Long) session.getAttribute("userId");
            if (id == null) {
                return new Message<String>(false, "NOT_LOGINED");
            }
            final User curUser = UserList.getById(id);
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

        public UserDao getUserDao() {
            return userDao;
        }

        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }
}
