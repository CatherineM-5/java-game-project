package treehugger.zones;
import static treehugger.Constants.*;
import static treehugger.zones.properties.Area.OCEAN;

/**
 * Concrete implementation of PlayableZone.
 */
public class OceanZone extends PlayableZone{

    public OceanZone(String zoneName) {
        super(OCEAN, zoneName, INITIAL_COST_OCEAN, STANDARD_DEV_COST, UPGRADED_DEV_COST, BASE_FEE_OCEAN, ONE_DEV_FEE_OCEAN, MAJOR_DEV_FEE_OCEAN);
    }
}
