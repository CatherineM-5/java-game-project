package treehugger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static treehugger.Constants.*;

class TreeHuggerTest {

    TreeHugger testTreeHugger;
    List<Player> testPlayers;
    Player testPlayerOne;
    Player testPlayerTwo;


    @BeforeEach
    void before(){
        testPlayers = populateTestPlayers();
        testTreeHugger = new TreeHugger(testPlayers);
    }

    @Test
    void testGameRoundBeginsAtZero(){
        assertEquals(GAME_STARTING_ROUND, testTreeHugger.getGameRound());
    }

    @Test
    void testGameRoundCanBeSetToValidValue(){
        testTreeHugger.setGameRound(1);
        assertEquals(1, testTreeHugger.getGameRound());
    }

    @Test
    void testGameIsPlayingIsTrueWhenGameCreated(){
        assertEquals(GAME_IS_PLAYING, testTreeHugger.isPlayingGame());
    }

    @Test
    void testGameIsPlayingCanBeSetToFalseToQuitGame(){
        testTreeHugger.setPlayingGame(false);
        assertEquals(GAME_IS_NOT_PLAYING, testTreeHugger.isPlayingGame());
    }

    @Test
    void testMinNumberOfPlayersCanBeRetrieved(){
        assertEquals(GAME_MIN_PLAYERS, testTreeHugger.getMinPlayers());
    }

    @Test
    void testMaxNumberOfPlayersCanBeRetrieved(){
        assertEquals(GAME_MAX_PLAYERS, testTreeHugger.getMaxPlayers());
    }

    //testgetplayers
    //testsetplayers

    @Test
    void testCurrentPlayerCanBeRetrieved(){
        assertEquals(testPlayerOne, testTreeHugger.getCurrentPlayer());
    }

    @Test
    void testCurrentPlayerCanBeSetForTheirTurn(){
        testTreeHugger.setCurrentPlayer(testPlayerTwo);
        assertEquals(testPlayerTwo, testTreeHugger.getCurrentPlayer());
    }

    @Test
    void testListOfPlayersCanBeRetrieved(){
        assertEquals(testPlayers, testTreeHugger.getPlayers());
    }

    @Test
    void testListOfPlayersCanBeSetForNewGame(){
        Player testPlayerAlpha = new Player("Rob", "PHP Inc!");
        Player testPlayerBeta = new Player("Dan", "Java Is Great Ltd");

        List<Player> updatedPlayers = new ArrayList<>();
        updatedPlayers.add(testPlayerAlpha);
        updatedPlayers.add(testPlayerBeta);

        testTreeHugger.setPlayers(updatedPlayers);

        assertEquals(updatedPlayers, testTreeHugger.getPlayers());
    }

    private List<Player> populateTestPlayers(){
        testPlayerOne = new Player("Rob", "PHP Inc!");
        testPlayerTwo = new Player("Dan", "Java Is Great Ltd");

        List<Player> players = new ArrayList<>();
        players.add(testPlayerOne);
        players.add(testPlayerTwo);

        return players;
    }

}