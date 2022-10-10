package treehugger;

import treehugger.zones.PlayableZone;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.compare;
import static treehugger.Constants.BOARD_POSITION_1;
import static treehugger.Constants.PLAYER_STARTING_BALANCE;

/**
 * Object to store data for a Player of the game.
 */
public class Player implements Comparable {

    private String playerName;
    private String companyName;
    private int playerRECBalance;
    private int playerPosition;

    private final ArrayList<PlayableZone> ownedZones = new ArrayList<>();

    public Player(String name, String companyName) {
        this.playerName=name;
        this.companyName = companyName;
        this .playerRECBalance = PLAYER_STARTING_BALANCE;
        this.playerPosition = BOARD_POSITION_1;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getPlayerRECBalance() {
        return playerRECBalance;
    }

    public void setPlayerRECBalance(int playerRECBalance) {
        this.playerRECBalance = playerRECBalance;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public List<PlayableZone> getOwnedZones() {
        return ownedZones;
    }

    public void addNewOwnedZone(PlayableZone newZone) {
        ownedZones.add(newZone);
    }

    public int movePlayer(int diceValue) {

        int newPosition = this.playerPosition + diceValue;

        if(newPosition > 12) {
            newPosition = newPosition - 12;
        }

        setPlayerPosition(newPosition);

        return this.playerPosition;
    }

    @Override
    public int compareTo(Object o) {
        return  (compare(this.getPlayerRECBalance(), ((Player) o).getPlayerRECBalance()));
    }
}