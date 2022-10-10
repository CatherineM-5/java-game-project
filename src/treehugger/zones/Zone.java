package treehugger.zones;

import treehugger.zones.properties.Area;

/**
 * Abstract class Zone to be extended in concrete implementation, providing common field Area.
 */
public abstract class Zone {

    private final Area area;

    private final String zoneName;

    Zone(Area area, String zoneName) {
        this.area = area;
        this.zoneName = zoneName;
    }

    public Area getArea() {
        return area;
    }

    public String getZoneName() {
        return zoneName;
    }
}
