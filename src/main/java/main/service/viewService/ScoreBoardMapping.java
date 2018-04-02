package main.service.viewService;

import main.models.User;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreBoardMapping {

    public static HashMap<String,String> getScoreInformation(User user) {
        if (user == null) {
            return null;
        }
        final HashMap<String,String> scoreMap = new HashMap<>();
        scoreMap.put("index", String.valueOf(user.getId()));
        scoreMap.put("nickname", user.getLogin());
        scoreMap.put("score", String.valueOf(user.getScore()));
        return scoreMap;
    }

    public static List<String> getMeInformation(User user) {
        final List<String> list = new ArrayList<>();
        if (user != null) {
            list.add(String.valueOf(user.getId()));
        } else {
            return null;
        }
        return list;
    }
}
