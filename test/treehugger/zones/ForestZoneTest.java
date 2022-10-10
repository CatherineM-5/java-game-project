package treehugger.zones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static treehugger.zones.properties.Area.FOREST;
import static treehugger.zones.properties.Level.MAJOR_DEVELOPMENT;
import static treehugger.zones.properties.Level.UNDEVELOPED;
import static treehugger.TestUtil.*;

class ForestZoneTest {

    private static ForestZone testForestZone;
    private String testZoneName;

    @BeforeEach
    void before(){
        testZoneName = "testZoneName";
        testForestZone = new ForestZone(testZoneName);
    }

    @Test
    void testGetForestZoneName(){ assertEquals(testZoneName, testForestZone.getZoneName());}

    @Test
    void testGetForestZoneLevel() {
        assertEquals(UNDEVELOPED, testForestZone.getLevel());
    }

    @Test
    void testGetForestZoneInitialCost() {
        assertEquals(TEST_INITIAL_COST_FOREST, testForestZone.getInitialCost());
    }

    @Test
    void testGetForestZoneStandardCost() {
        assertEquals(TEST_STANDARD_DEV_COST, testForestZone.getStandardCost());
    }

    @Test
    void testGetForestZoneUpgradedCost() {
        assertEquals(TEST_UPGRADED_DEV_COST, testForestZone.getUpgradedCost());
    }

    @Test
    void testGetForestZoneBaseFee() {
        assertEquals(TEST_BASE_FEE_FOREST, testForestZone.getBaseFee());
    }

    @Test
    void testGetForestZoneStandardFee() {
        assertEquals(TEST_ONE_DEV_FEE_FOREST, testForestZone.getStandardFee());
    }

    @Test
    void testGetForestZoneUpgradedFee() {
        assertEquals(TEST_MAJOR_DEV_FEE_FOREST, testForestZone.getUpgradedFee());
    }

    @Test
    void testGetForestZoneArea() {
        assertEquals(FOREST, testForestZone.getArea());
    }

    @Test
    void testSetForestZoneLevel() {
        testForestZone.setLevel(MAJOR_DEVELOPMENT);
        assertEquals(MAJOR_DEVELOPMENT, testForestZone.getLevel());
    }

}