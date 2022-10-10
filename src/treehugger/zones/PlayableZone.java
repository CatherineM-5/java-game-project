package treehugger.zones;

import treehugger.zones.properties.Area;
import treehugger.zones.properties.Level;
import treehugger.Player;

import static treehugger.Constants.IS_NOT_OWNED;
import static treehugger.zones.properties.Level.UNDEVELOPED;

/**
 * Extension of Zone object for zones which the Player will have active interaction with.
 */
public abstract class PlayableZone extends Zone{

    private final int initialCost;
    private final int standardCost;
    private final int upgradedCost;
    private final int baseFee;
    private final int standardFee;
    private final int upgradedFee;

    private Level level;
    private Player owner;
    private boolean owned;

    PlayableZone(Area area, String zoneName, int initialCost, int standardCost, int upgradedCost, int baseFee, int standardFee, int upgradedFee) {
        super(area, zoneName);
        this.level = UNDEVELOPED;
        this.initialCost = initialCost;
        this.standardCost = standardCost;
        this.upgradedCost = upgradedCost;
        this.baseFee = baseFee;
        this.standardFee =standardFee;
        this.upgradedFee = upgradedFee;
        this.owned = IS_NOT_OWNED;
        this.owner = null;
    }

    public Level getLevel() {
        return level;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public int getStandardCost() {
        return standardCost;
    }

    public int getUpgradedCost() {
        return upgradedCost;
    }

    public int getBaseFee() {
        return baseFee;
    }

    public int getStandardFee() {
        return standardFee;
    }

    public int getUpgradedFee() {
        return upgradedFee;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
