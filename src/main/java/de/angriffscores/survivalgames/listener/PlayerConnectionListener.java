package de.angriffscores.survivalgames.listener;

import de.angriffscores.survivalgames.SurvivalGames;
import de.angriffscores.survivalgames.countdowns.LobbyCountdown;
import de.angriffscores.survivalgames.gamestates.EndingState;
import de.angriffscores.survivalgames.gamestates.IngameState;
import de.angriffscores.survivalgames.gamestates.LobbyState;
import de.angriffscores.survivalgames.utils.Constants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private SurvivalGames survivalGames;

    public PlayerConnectionListener(SurvivalGames survivalGames){
        this.survivalGames = survivalGames;
    }

    @EventHandler
    public void handleJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(survivalGames.getGameStateManager().getCurrentGameState() instanceof LobbyState){
            Constants.players.add(player);
            event.setJoinMessage(Constants.prefix+"§a"+player.getName()+" §7ist dem Spiel beigetreten. [§a"+
                    Constants.players.size()+"§7/§c"+Constants.MAX_PLAYERS+"§7]");

            LobbyState lobbyState = (LobbyState) survivalGames.getGameStateManager().getCurrentGameState();
            LobbyCountdown lobbyCountdown = lobbyState.getCountdown();
            if(Constants.players.size() >= Constants.MIN_PLAYERS){
                if(lobbyCountdown.isRunning())return;
                lobbyCountdown.stopIdle();
                lobbyCountdown.start();
            }


        }else if(survivalGames.getGameStateManager().getCurrentGameState() instanceof IngameState){
            event.setJoinMessage(null);

        }else if(survivalGames.getGameStateManager().getCurrentGameState() instanceof EndingState){
            player.kickPlayer("§r");
        }
    }

    @EventHandler
    public void handleQuit(PlayerQuitEvent event){
        Player player  = event.getPlayer();

        if(survivalGames.getGameStateManager().getCurrentGameState() instanceof LobbyState){
            Constants.players.remove(player);
            event.setQuitMessage(Constants.prefix+"§a"+player.getName()+" §7hat das Spiel verlassen. [§a"+
                    Constants.players.size()+"§7/§c"+Constants.MAX_PLAYERS+"§7]");

            LobbyState lobbyState = (LobbyState) survivalGames.getGameStateManager().getCurrentGameState();
            LobbyCountdown lobbyCountdown = lobbyState.getCountdown();
            if(Constants.players.size() < Constants.MIN_PLAYERS){
                if(lobbyCountdown.isRunning()){
                    lobbyCountdown.stop();
                    lobbyCountdown.startIdle();
                }
            }

        }else if(survivalGames.getGameStateManager().getCurrentGameState() instanceof IngameState){
            if(Constants.players.contains(player))
                Constants.players.remove(player);

        }else if(survivalGames.getGameStateManager().getCurrentGameState() instanceof EndingState){
            if(Constants.players.contains(player))
                    Constants.players.remove(player);
        }
    }
}
