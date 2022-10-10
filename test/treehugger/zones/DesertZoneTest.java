package treehugger.zones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static treehugger.zones.properties.Area.DESERT;
import static treehugger.zones.properties.Level.MAJOR_DEVELOPMENT;
import static treehugger.zones.properties.Level.UNDEVELOPED;
import static treehugger.TestUtil.*;

class DesertZoneTest {

    private static DesertZone testDesertZone;
    private String testZoneName;

    @BeforeEach
    void before(){
        testZoneName = "testZoneName";
        testDesertZone = new DesertZone(testZoneName);
    }

    @Test
    void testGetDesertZoneName(){ assertEquals(testZoneName, testDesertZone.getZoneName());}

    @Test
    void testGetDesertZoneLevel() {
        assertEquals(UNDEVELOPED, testDesertZone.getLevel());
    }

    @Test
    void testGetDesertZoneInitialCost() {
        assertEquals(TEST_INITIAL_COST_DESERT, testDesertZone.getInitialCost());
    }

    @Test
    void testGetDesertZoneStandardCost() {
        assertEquals(TEST_STANDARD_DEV_COST, testDesertZone.getStandardCost());
    }

    @Test
    void testGetDesertZoneUpgradedCost() {
        assertEquals(TEST_UPGRADED_DEV_COST, testDesertZone.getUpgradedCost());
    }

    @Test
    void testGetDesertZoneBaseFee() {
        assertEquals(TEST_BASE_FEE_DESERT, testDesertZone.getBaseFee());
    }

    @Test
    void testGetDesertZoneStandardFee() {
        assertEquals(TEST_ONE_DEV_FEE_DESERT, testDesertZone.getStandardFee());
    }

    @Test
    void testGetDesertZoneUpgradedFee() {
        assertEquals(TEST_MAJOR_DEV_FEE_DESERT, testDesertZone.getUpgradedFee());
    }

    @Test
    void testGetDesertZoneArea() {
        assertEquals(DESERT, testDesertZone.getArea());
    }

    @Test
    void testSetDesertZoneLevel() {
        testDesertZone.setLevel(MAJOR_DEVELOPMENT);
        assertEquals(MAJOR_DEVELOPMENT, testDesertZone.getLevel());
    }
}