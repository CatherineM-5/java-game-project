package treehugger.zones;

import treehugger.zones.properties.Area;

/**
 * Implementation of Zone for zones which the player has passive interaction with.
 */
public class NonPlayableZone extends Zone {

    public NonPlayableZone(Area area, String zoneName) {
        super(area, zoneName);
    }
}
