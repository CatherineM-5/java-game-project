package treehugger;

import java.io.IOException;

public class StartApp {

    /**
     * Private constructor to avoid creation of object for utility class.
     */
    private StartApp(){

    }

    public static void main(String[] args) {

        try {
            GameUtil.startGame();
        } catch(IOException e) {
            System.out.print("Some of the necessary files for the game have not been found. Please try re-downloading.");
        }

    }
}

