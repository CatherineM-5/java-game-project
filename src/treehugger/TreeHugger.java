package treehugger;

import java.util.*;

/**
 * Implementation of Game object.
 */
public class TreeHugger extends Game {

    private Player currentPlayer;

    private List<Player> players;
    
    public TreeHugger(List<Player> players){
        super();
        this.players = players;
        this.currentPlayer = players.get(0);
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
