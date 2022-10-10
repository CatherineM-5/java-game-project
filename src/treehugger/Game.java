package treehugger;

import static treehugger.Constants.*;

/**
 * Abstract class to be extended for each type of Game.
 */
public abstract class Game {

    private int gameRound;
    private boolean playingGame;
    private final int minPlayers;
    private final int maxPlayers;

    Game(){
        this.gameRound = GAME_STARTING_ROUND;
        this.playingGame = GAME_IS_PLAYING;
        this.minPlayers = GAME_MIN_PLAYERS;
        this.maxPlayers = GAME_MAX_PLAYERS;
    }

    public int getGameRound() {
        return gameRound;
    }

    public void setGameRound(int gameRound) {
        this.gameRound = gameRound;
    }

    public boolean isPlayingGame() {
        return playingGame;
    }

    public void setPlayingGame(boolean playingGame) {
        this.playingGame = playingGame;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

}
