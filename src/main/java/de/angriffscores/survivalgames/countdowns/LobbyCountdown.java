package de.angriffscores.survivalgames.countdowns;

import de.angriffscores.survivalgames.gamestates.GameStateManager;
import de.angriffscores.survivalgames.utils.Constants;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown {

    private int seconds;
    private int idleID;
    private boolean isIdling;
    private boolean isRunning;

    private GameStateManager gameStateManager;

    public LobbyCountdown(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        seconds = Constants.COUNTDOWN_TIME;
    }

    @Override
    public void start(){
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getSurvivalGames(), new Runnable() {
            @Override
            public void run() {
                switch (seconds){
                    case 90: case 60: case 30: case 15: case 10: case 5: case 3: case 2:
                        Bukkit.broadcastMessage(Constants.prefix+"§7Das Spiel startet in §a"+seconds+" Sekunden§7.");
                        break;

                    case 1:
                        Bukkit.broadcastMessage(Constants.prefix+"§7Das Spiel startet in §aeiner Sekunden§7.");
                        break;

                    case 0:
                        break;

                    default:
                        break;
                }
                seconds--;
            }
        },0, 20);
    }

    @Override
    public void stop(){
        if(isRunning){
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = Constants.COUNTDOWN_TIME;
        }
    }

    public void startIdle(){
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getSurvivalGames(), () ->
                Bukkit.broadcastMessage(Constants.prefix+"§7Bis zum Spielstart fehlen noch §6"+
                        (Constants.MIN_PLAYERS - Constants.players.size())+ " §7Spieler."), 0, 20*15);
    }

    public void stopIdle(){
        if(isIdling){
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
