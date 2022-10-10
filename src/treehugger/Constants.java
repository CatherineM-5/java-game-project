package treehugger;

/**
 * Utility class to store constants used throughout.
 */
public class Constants {

    /**
     * Private constructor to avoid creation of object for utility class.
     */
    private Constants() {

    }

    public static final int BOARD_POSITION_1 = 1;
    public static final int BOARD_POSITION_2 = 2;
    public static final int BOARD_POSITION_3 = 3;
    public static final int BOARD_POSITION_4 = 4;
    public static final int BOARD_POSITION_5 = 5;
    public static final int BOARD_POSITION_6 = 6;
    public static final int BOARD_POSITION_7 = 7;
    public static final int BOARD_POSITION_8 = 8;
    public static final int BOARD_POSITION_9 = 9;
    public static final int BOARD_POSITION_10 = 10;
    public static final int BOARD_POSITION_11 = 11;
    public static final int BOARD_POSITION_12 = 12;
    public static final int PLAYER_STARTING_BALANCE = 500;
    public static final int INITIAL_COST_OCEAN = 150;
    public static final int INITIAL_COST_DESERT = 150;
    public static final int INITIAL_COST_FOREST = 300;
    public static final int INITIAL_COST_URBAN = 100;
    public static final int STANDARD_DEV_COST=50;
    public static final int UPGRADED_DEV_COST = 100;
    public static final int BASE_FEE_OCEAN = 30;
    public static final int BASE_FEE_DESERT = 30;
    public static final int BASE_FEE_FOREST = 60;
    public static final int BASE_FEE_URBAN = 20;
    public static final int ONE_DEV_FEE_OCEAN = 40;
    public static final int ONE_DEV_FEE_DESERT = 40;
    public static final int ONE_DEV_FEE_FOREST = 70;
    public static final int ONE_DEV_FEE_URBAN = 30;
    public static final int MAJOR_DEV_FEE_OCEAN = 60;
    public static final int MAJOR_DEV_FEE_DESERT = 60;
    public static final int MAJOR_DEV_FEE_FOREST = 90;
    public static final int MAJOR_DEV_FEE_URBAN = 50;
    public static final int PASS_G8_SUMMIT_REWARD = 200;
    public static final int GAME_STARTING_ROUND = 0;
    public static final int GAME_MIN_PLAYERS = 2;
    public static final int GAME_MAX_PLAYERS = 4;
    public static final int FIRST_ROUND = 1;
    public static final int DICE_MIN = 1;
    public static final int DICE_MAX = 12;
    public static final int DEVELOPMENT_TWO_FACTOR = 2;
    public static final int DEVELOPMENT_THREE_FACTOR = 3;
    public static final int LARGE_AREA_SIZE = 3;
    public static final int SMALL_AREA_SIZE = 2;

    public static final boolean GAME_IS_PLAYING = true;
    public static final boolean GAME_IS_NOT_PLAYING = false;
    public static final boolean IS_OWNED = true;
    public static final boolean IS_NOT_OWNED = false;

    private static final String GAME_FILES_ROOT_DIR = "./src/gameFiles/";
    public static final String LOGO_PATH =  GAME_FILES_ROOT_DIR + "logo.txt";
    public static final String RULES_PATH =  GAME_FILES_ROOT_DIR + "gameRules.txt";
    public static final String START_MENU_PATH =  GAME_FILES_ROOT_DIR + "startMenu.txt";
    public static final String PLAYER_CONFIRMATION = "Y";
    public static final String PLAYER_DECLINE = "N";
    public static final String PLAYER_QUIT = "Q";
    public static final String TOO_FEW_PLAYERS_WARN = "2 players min! Please try again!";
    public static final String TOO_MANY_PLAYERS_WARN = "4 players max! Please try again!";
    public static final String TIDAL_POWER_GENERATOR_S = "Tidal Power Generator Small";
    public static final String TIDAL_POWER_GENERATOR_M = "Tidal Power Generator Medium";
    public static final String TIDAL_POWER_GENERATOR_L = "Tidal Power Generator Large";
    public static final String SOLAR_FARM_S = "Solar Farm Small";
    public static final String SOLAR_FARM_M = "Solar Farm Medium";
    public static final String SOLAR_FARM_L = "Solar Farm Large";
    public static final String FOREST_PLANTATION_S = "Forest Plantation Small";
    public static final String FOREST_PLANTATION_L = "Forest Plantation Large";
    public static final String RECYCLING_SITE_S = "Recycling Site Small";
    public static final String RECYCLING_SITE_L = "Recycling Site Large";
    public static final String GO = "Go";
    public static final String HUG_A_TREE = "Hug a tree";
}

