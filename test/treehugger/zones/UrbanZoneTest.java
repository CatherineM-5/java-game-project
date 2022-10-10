package treehugger.zones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static treehugger.zones.properties.Area.URBAN;
import static treehugger.zones.properties.Level.MAJOR_DEVELOPMENT;
import static treehugger.zones.properties.Level.UNDEVELOPED;
import static treehugger.TestUtil.*;

class UrbanZoneTest {

    private UrbanZone testUrbanZone;
    private String testZoneName;

    @BeforeEach
    void before(){
        testZoneName = "testZoneName";
        testUrbanZone = new UrbanZone(testZoneName);
    }

    @Test
    void testGetUrbanZoneName(){ assertEquals(testZoneName, testUrbanZone.getZoneName());}

    @Test
    void testGetUrbanZoneLevel() {
        assertEquals(UNDEVELOPED, testUrbanZone.getLevel());
    }

    @Test
    void testGetUrbanZoneInitialCost() {
        assertEquals(TEST_INITIAL_COST_URBAN, testUrbanZone.getInitialCost());
    }

    @Test
    void testGetUrbanZoneStandardCost() {
        assertEquals(TEST_STANDARD_DEV_COST, testUrbanZone.getStandardCost());
    }

    @Test
    void testGetUrbanZoneUpgradedCost() {
        assertEquals(TEST_UPGRADED_DEV_COST, testUrbanZone.getUpgradedCost());
    }

    @Test
    void testGetUrbanZoneBaseFee() {
        assertEquals(TEST_BASE_FEE_URBAN, testUrbanZone.getBaseFee());
    }

    @Test
    void testGetUrbanZoneStandardFee() {
        assertEquals(TEST_ONE_DEV_FEE_URBAN, testUrbanZone.getStandardFee());
    }

    @Test
    void testGetUrbanZoneUpgradedFee() {
        assertEquals(TEST_MAJOR_DEV_FEE_URBAN, testUrbanZone.getUpgradedFee());
    }

    @Test
    void testGetUrbanZoneArea() {
        assertEquals(URBAN, testUrbanZone.getArea());
    }

    @Test
    void testSetUrbanZoneLevel() {
        testUrbanZone.setLevel(MAJOR_DEVELOPMENT);
        assertEquals(MAJOR_DEVELOPMENT, testUrbanZone.getLevel());
    }

}