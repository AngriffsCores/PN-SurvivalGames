package de.angriffscores.survivalgames;

import de.angriffscores.survivalgames.gamestates.GameState;
import de.angriffscores.survivalgames.gamestates.GameStateManager;
import de.angriffscores.survivalgames.listener.PlayerConnectionListener;
import de.angriffscores.survivalgames.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalGames extends JavaPlugin {

    private GameStateManager gameStateManager;

    @Override
    public void onEnable(){
        gameStateManager = new GameStateManager(this);

        gameStateManager.setGameState(GameState.LOBBY_STATE);

        initListener(Bukkit.getPluginManager());
        initCommands();
        Constants.initConstants();

        System.out.println("[SurvivalGames] Das Plugin wurde gestartet.");
    }

    @Override
    public void onDisable(){
        System.out.println("[SurvivalGames] Das Plugin wurde gestopp.");
    }

    public void initListener(PluginManager pluginManager){
        pluginManager.registerEvents(new PlayerConnectionListener(this), this);
    }

    public void initCommands(){

    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }
}
