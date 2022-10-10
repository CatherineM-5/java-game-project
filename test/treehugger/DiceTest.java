package treehugger;

import org.junit.jupiter.api.Test;

class DiceTest {

    @Test
    void testRollDiceReturnsIntWithinValidRange() {

        for(int i = 0; i<100; i++) {
            int diceRoll = Dice.rollDice();
            assert (diceRoll > 0 && diceRoll < 13);
            i++;
        }
    }

}