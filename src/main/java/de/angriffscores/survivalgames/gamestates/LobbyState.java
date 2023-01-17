package de.angriffscores.survivalgames.gamestates;

import de.angriffscores.survivalgames.countdowns.LobbyCountdown;

public class LobbyState extends GameState {

    private LobbyCountdown countdown;

    public LobbyState(GameStateManager gameStateManager){
        countdown = new LobbyCountdown(gameStateManager);
    }

    @Override
    public void start(){
        countdown.startIdle();
    }

    @Override
    public void stop(){

    }

    public LobbyCountdown getCountdown() {
        return countdown;
    }
}
