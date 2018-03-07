package main.data;

import main.models.Player;
import main.models.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class UserList {
    private static final HashMap<String, User> ARRAY = new HashMap<String, User>() {
        {
            final User first = new User("ivansport@gmail.com", "ivansport", "0");
            final User second = new User("vitaliycherkov@gmail.com", "vitaliycherkov", "1");
            final User third  = new User("vladbusov@gmail.com", "vladbusov", "2");

            put(first.getEmail(), first);
            put(second.getEmail(), second);
            put(third.getEmail(), third);
        }
    };

    public static void addUser(User newbie) {
        ARRAY.put(newbie.getEmail(), newbie);
    }

    public static void deleteUser(String email) {
        ARRAY.remove(email);
    }

    public static boolean login(String email, String password) {
        if (!StringUtils.isEmpty(email)) {
            final User current = ARRAY.get(email);
            if (current != null) {
                return current.getPassword().equals(password);
            }
        }
        return false;
    }

    public static boolean uniqueUser(String email) {
        return !ARRAY.containsKey(email);
    }

    public static @Nullable Long getId(String email) {
        if (!ARRAY.containsKey(email)) {
            return null;
        }
        return ARRAY.get(email).getId();
    }

    public static @Nullable User getById(Long id) {
        for (User curUser : ARRAY.values()) {
            if (curUser.getId().equals(id)) {
                return curUser;
            }
        }
        return null;
    }

    public static ArrayList<Player> toArrayListPlayersWithoutId(@Nullable Long id) {
        final ArrayList<Player> players = new ArrayList<>();
        for (User curUser : ARRAY.values()) {
            if (!curUser.getId().equals(id)) {
                players.add(new Player(curUser));
            }
        }
        return players;
    }

}
