package treehugger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import treehugger.zones.OceanZone;
import treehugger.zones.Zone;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player testPlayer;
    Player testPlayer2;

    @BeforeEach
    void before(){
        testPlayer = new Player("Bob", "Blonde Inc");
        testPlayer2 = new Player("Til", "Ramm Ltd");
    }

    @Test
    void getPlayerName() {
        assertEquals("Bob", testPlayer.getPlayerName());
    }

    @Test
    void setPlayerName() {
        testPlayer.setPlayerName("Henry");
        assertEquals("Henry", testPlayer.getPlayerName());
    }

    @Test
    void getCompanyName() {
        testPlayer.setCompanyName("EcoWarriors");
        assertEquals("EcoWarriors", testPlayer.getCompanyName());
    }

    @Test
    void setCompanyName() {
        testPlayer.setCompanyName("EcoFriendlyCo");
        assertEquals("EcoFriendlyCo", testPlayer.getCompanyName());
    }

    @Test
    void getPlayerRECBalance() {
        assertEquals(500, testPlayer.getPlayerRECBalance());
    }

    @Test
    void setPlayerRECBalance() {
        testPlayer.setPlayerRECBalance(1500);
        assertEquals(1500, testPlayer.getPlayerRECBalance());
    }

    @Test
    void getPlayerPosition() {
        assertEquals(1, testPlayer.getPlayerPosition());
    }

    @Test
    void setPlayerPosition() {
        testPlayer.setPlayerPosition(7);
        assertEquals(7, testPlayer.getPlayerPosition());
    }

    @Test
    void testOwnedZones(){
        OceanZone testZone1 = new OceanZone("testZone");
        OceanZone testZone2 = new OceanZone("testZone");

        List<Zone> testZones = new ArrayList<>();
        testZones.add(testZone1);
        testZones.add(testZone2);

        testPlayer.addNewOwnedZone(testZone1);
        testPlayer.addNewOwnedZone(testZone2);

        assertEquals(testZones, testPlayer.getOwnedZones());
        assertEquals(testZone1, testPlayer.getOwnedZones().get(0));
        assertEquals(testZone2, testPlayer.getOwnedZones().get(1));
    }

    @Test
    void testMovePlayer() {
        testPlayer.movePlayer(5);

        assertEquals(6, testPlayer.getPlayerPosition());
    }

    @Test
    void testMovePlayerPastStartingPosition() {
        testPlayer.movePlayer(13);

        assertEquals(2, testPlayer.getPlayerPosition());
    }

    @Test
    void testCompareTo(){
        testPlayer.setPlayerRECBalance(1000);
        testPlayer2.setPlayerRECBalance(900);

        
    }
}