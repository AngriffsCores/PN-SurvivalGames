package de.angriffscores.survivalgames.gamestates;

import de.angriffscores.survivalgames.SurvivalGames;

public class GameStateManager {

    private SurvivalGames survivalGames;
    private GameState[] gameStates;
    private GameState currentGameState;

    public GameStateManager(SurvivalGames survivalGames){
        this.survivalGames = survivalGames;
        gameStates = new GameState[3];

        gameStates[GameState.LOBBY_STATE] = new LobbyState(this);
        gameStates[GameState.INGAME_STATE] = new IngameState();
        gameStates[GameState.ENDING_STATE] = new EndingState();
    }

    public void setGameState(int gameStateID){
        if(currentGameState != null)
            currentGameState.stop();
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }

    public void stopCurrentGameState(){
        if(currentGameState != null){
            currentGameState.stop();
            currentGameState = null;
        }
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public GameState[] getGameStates() {
        return gameStates;
    }

    public SurvivalGames getSurvivalGames() {
        return survivalGames;
    }
}
