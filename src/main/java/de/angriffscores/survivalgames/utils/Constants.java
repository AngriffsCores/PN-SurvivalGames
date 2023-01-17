package de.angriffscores.survivalgames.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static int MIN_PLAYERS,
                      MAX_PLAYERS,
                      COUNTDOWN_TIME;

    public static String prefix,
                         noPermission;

    public static List<Player> players;

    public static void initConstants(){
        players = new ArrayList<>();

        MIN_PLAYERS = 2;
        MAX_PLAYERS = 24;
        COUNTDOWN_TIME = 20;

        prefix = "§7[§aSurvivalGames§7]";
        noPermission = prefix+"§cDu hast nicht genügend Rechte.";
    }
}
