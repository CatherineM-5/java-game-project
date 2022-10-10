package treehugger;

import treehugger.zones.Zone;

import java.util.Map;

/**
 * Board class containing different types of zones for players to move around.
 */
public class Board {

    private Map<Integer, Zone> zones;

    public Board(Map<Integer,Zone> zones) {
        this.zones = zones;
    }

    public Map<Integer, Zone> getZones() {
        return zones;
    }

    public void setZones(Map<Integer, Zone> zones) {
        this.zones = zones;
    }

    public Zone getZone(int boardPosition) {

        return zones.get(boardPosition);
    }

}
