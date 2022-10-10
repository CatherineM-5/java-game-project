package treehugger;

import java.util.Random;

import static treehugger.Constants.DICE_MAX;
import static treehugger.Constants.DICE_MIN;

/**
 * Utility class for Dice functionality.
 */
public class Dice {

    private static final Random RANDOM = new Random();

    /**
     * Private constructor to avoid creation of object for utility class.
     */
    private Dice() {
    }

    /**
     *  Return random int value for a Player's dice roll.
     *
     * @return Random integer value of between 1 and 12.
     */
    public static int rollDice() {
        int min = DICE_MIN;
        int max = DICE_MAX;
        return RANDOM.nextInt((max - min) + 1) + min;
    }

}




