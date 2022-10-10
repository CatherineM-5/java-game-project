package treehugger.zones;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import treehugger.zones.properties.Area;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static treehugger.zones.properties.Area.G8_SUMMIT;

class NonPlayableZoneTest {

    private static NonPlayableZone testZone;
    private static Area testArea = G8_SUMMIT;
    private static String testZoneName = "testZoneName";

    @BeforeAll
    static void before(){
        testZone = new NonPlayableZone(testArea, testZoneName);
    }

    @Test
    void testZoneAreaCanBeRetrievedFromNonPlayableZone() {
        assertEquals(testArea, testZone.getArea());
    }

    @Test
    void testZoneNameCanBeRetrievedFromNonPlayableZone() {
        assertEquals(testZoneName, testZone.getZoneName());
    }
}