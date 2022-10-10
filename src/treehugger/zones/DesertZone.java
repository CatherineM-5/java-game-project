package treehugger.zones;
import static treehugger.Constants.*;
import static treehugger.zones.properties.Area.DESERT;

/**
 * Concrete implementation of PlayableZone.
 */
public class DesertZone extends PlayableZone{

    public DesertZone(String zoneName) {
        super(DESERT,  zoneName, INITIAL_COST_DESERT, STANDARD_DEV_COST, UPGRADED_DEV_COST, BASE_FEE_DESERT, ONE_DEV_FEE_DESERT, MAJOR_DEV_FEE_DESERT);
    }
}
