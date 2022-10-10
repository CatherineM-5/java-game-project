package treehugger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import treehugger.zones.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private HashMap<Integer, Zone> testZones;
    private Board testBoard;
    private OceanZone testZone1;
    private DesertZone testZone2;

    @BeforeEach
    void before(){
        String testZoneName1 = "testZoneName1";
        String testZoneName2 = "testZoneName2";

        testZone1 = new OceanZone(testZoneName1);
        testZone2 = new DesertZone(testZoneName2);

        testZones = new HashMap<>();
        testZones.put(1, testZone1);
        testZones.put(2, testZone2);

        testBoard = new Board(testZones);
    }

    @Test
    void getZones() {
        assertEquals(testZones, testBoard.getZones());
    }

    @Test
    void setZones() {
        String testZoneName3 = "testZoneName3";
        String testZoneName4 = "testZoneName4";

        ForestZone testZoneAlpha = new ForestZone(testZoneName3);
        UrbanZone testZoneBeta = new UrbanZone(testZoneName4);

        HashMap<Integer, Zone> testZones2 = new HashMap<>();
        testZones2.put(1, testZoneAlpha);
        testZones2.put(2, testZoneBeta);

        testBoard.setZones(testZones2);

        assertEquals(testZones2, testBoard.getZones());
    }

    @Test
    void getZone() {
        assertEquals(testZone1, testBoard.getZone(1));
    }
}