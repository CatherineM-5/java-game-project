package treehugger.zones;
import static treehugger.Constants.*;
import static treehugger.zones.properties.Area.URBAN;

/**
 * Concrete implementation of PlayableZone.
 */
public class UrbanZone extends PlayableZone{

    public UrbanZone(String zoneName) {
        super(URBAN, zoneName, INITIAL_COST_URBAN, STANDARD_DEV_COST, UPGRADED_DEV_COST, BASE_FEE_URBAN, ONE_DEV_FEE_URBAN, MAJOR_DEV_FEE_URBAN);
    }
}
