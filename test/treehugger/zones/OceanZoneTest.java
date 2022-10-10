package treehugger.zones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static treehugger.zones.properties.Area.OCEAN;
import static treehugger.zones.properties.Level.MAJOR_DEVELOPMENT;
import static treehugger.zones.properties.Level.UNDEVELOPED;
import static treehugger.TestUtil.*;

class OceanZoneTest {

    private static OceanZone testOceanZone;
    private String testZoneName;

    @BeforeEach
    void before(){
        testZoneName = "testZoneName";
        testOceanZone = new OceanZone(testZoneName);
    }

    @Test
    void testGetOceanZoneName(){ assertEquals(testZoneName, testOceanZone.getZoneName());}

    @Test
    void testGetOceanZoneLevel() {
        assertEquals(UNDEVELOPED, testOceanZone.getLevel());
    }

    @Test
    void testGetOceanZoneInitialCost() {
        assertEquals(TEST_INITIAL_COST_DESERT, testOceanZone.getInitialCost());
    }

    @Test
    void testGetOceanZoneStandardCost() {
        assertEquals(TEST_STANDARD_DEV_COST, testOceanZone.getStandardCost());
    }

    @Test
    void testGetOceanZoneUpgradedCost() {
        assertEquals(TEST_UPGRADED_DEV_COST, testOceanZone.getUpgradedCost());
    }

    @Test
    void testGetOceanZoneBaseFee() {
        assertEquals(TEST_BASE_FEE_DESERT, testOceanZone.getBaseFee());
    }

    @Test
    void testGetOceanZoneStandardFee() {
        assertEquals(TEST_ONE_DEV_FEE_OCEAN, testOceanZone.getStandardFee());
    }

    @Test
    void testGetOceanZoneUpgradedFee() {
        assertEquals(TEST_MAJOR_DEV_FEE_OCEAN, testOceanZone.getUpgradedFee());
    }

    @Test
    void testGetOceanZoneArea() {
        assertEquals(OCEAN, testOceanZone.getArea());
    }

    @Test
    void testSetOceanZoneLevel() {
        testOceanZone.setLevel(MAJOR_DEVELOPMENT);
        assertEquals(MAJOR_DEVELOPMENT, testOceanZone.getLevel());
    }

}