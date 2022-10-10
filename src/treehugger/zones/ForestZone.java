package treehugger.zones;
import static treehugger.Constants.*;
import static treehugger.zones.properties.Area.FOREST;

public class ForestZone extends PlayableZone{

    public ForestZone(String zoneName) {
        super(FOREST, zoneName, INITIAL_COST_FOREST, STANDARD_DEV_COST, UPGRADED_DEV_COST, BASE_FEE_FOREST, ONE_DEV_FEE_FOREST, MAJOR_DEV_FEE_FOREST);
    }
}
