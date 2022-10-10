package treehugger;

import treehugger.zones.*;
import treehugger.zones.properties.Area;
import treehugger.zones.properties.Level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static treehugger.Constants.*;
import static treehugger.zones.properties.Area.*;
import static treehugger.zones.properties.Level.*;

/**
 * Utility class including methods to run the game.
 */
public class GameUtil {

    private static final Scanner scan = new Scanner(System.in);
    private static final String PLAYER_NAME_REGEX = "[a-zA-Z\\s]+";
    private static final String COMPANY_NAME_REGEX = "[\\w\\s!@]+";

    private static List<Player> players;

    /**
     * Private constructor to avoid creation of object for utility class.
     */
    private GameUtil() {

    }

    /**
     * Player selects one of three options to start playing, read the rules or quit the game
     * @throws IOException
     */
    public static void startGame() throws IOException {
        displayFromPath(LOGO_PATH);
        display("Please choose one of the following options:");

        int option = 0;

        do {
            displayFromPath(START_MENU_PATH);

            try {
                option = scan.nextInt();

                switch (option) {
                    case 1:
                        preparePlayers();
                        TreeHugger treeHugger = new TreeHugger(players);
                        startPlaying(treeHugger);
                        break;
                    case 2:
                        displayFromPath(RULES_PATH);
                        break;
                    case 3:
                        break;
                    default:
                        display("Try options again...");
                }
            } catch (InputMismatchException e) {
                display("Please enter a valid number");
                scan.nextLine();
            } catch (IOException e) {
                display("It appears you do not have the game rules file. Please re-download"+"\nor try locating the file and placing it in gameFiles folder.");
            } catch (Exception e) {
                display("exception e : " + e.getMessage());
                display("Something went wrong... try again.");
            }

        } while (option != 3);
        scan.close();
        display("Thank you for playing TreeHuggers!");
    }

    /**
     * Sets the number of players that will be taking part in the game
     */
    private static void preparePlayers() {
        display("STEP 1");
        display("Enter how many players will be playing (2-4)");

        int numPlayers = determineNumberOfPlayers();

        display("---------------------");
        display("STEP 2");

        players = populatePlayers(numPlayers);
    }

    /**
     * Adds the total number of players between 2-4 to an Arraylist
     * @param numPlayers
     * @return number of players
     */
    private static List<Player> populatePlayers(int numPlayers) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            players.add(addPlayer(players));
        }

        return players;
    }

    /**
     *
     * @param players
     * @return the players enter name and chosen company name
     */
    private static Player addPlayer(List<Player> players) {
        String playerName = determinePlayerName(players);
        String companyName = determineCompanyName(players);

        display("Thank you " + playerName + " :) " + "Welcome to the treehugger game!");

        return new Player(playerName, companyName);
    }

    /**
     *
     * @param players
     * @return
     */
    private static String determinePlayerName(List<Player> players) {
        display("Enter the name of new Tree Hugger player : ");

        String playerName = scan.next();
        scan.nextLine();

        while (!validatePlayerName(PLAYER_NAME_REGEX, playerName, players)){
            display("Invalid player name. Please Try Again!");

            playerName = scan.next();
        }

        return playerName;
    }

    /**
     *
     * @param name
     * @param players
     * @return
     */
    private static boolean validatePlayerName(String regex, String name, List<Player> players){
        return name.matches(regex) && isPlayerNameUnique(name, players);
    }

    /**
     *
     * @param name
     * @param players
     * @return
     */
    private static boolean isPlayerNameUnique(String name, List<Player> players){

        for(Player player : players){
            if(player.getPlayerName().equalsIgnoreCase(name)){
                return false;
            }
        }

        return true;
    }

    /**
     * @return
     */
    private static String determineCompanyName(List<Player> players) {
        display("Enter the name of Company : ");

        String companyName = scan.next();
        scan.nextLine();

        while (!validateCompanyName(COMPANY_NAME_REGEX, companyName, players)){
            display("Invalid company name. Please Try Again!");

            companyName = scan.next();
        }

        return companyName;
    }

    /**
     *
     * @param name
     * @param players
     * @return
     */
    private static boolean validateCompanyName(String regex, String name, List<Player> players){
        return name.matches(regex) && isCompanyNameUnique(name, players);
    }


    /**
     *
     * @param name
     * @param players
     * @return
     */
    private static boolean isCompanyNameUnique(String name, List<Player> players){

        for(Player player : players){
            if(player.getCompanyName().equalsIgnoreCase(name)){
                return false;
            }
        }

        return true;
    }

    /**
     * @return
     */
    private static int determineNumberOfPlayers() {
        int numPlayers = scan.nextInt();

        if (numPlayers < GAME_MIN_PLAYERS) {
            display(TOO_FEW_PLAYERS_WARN);
            numPlayers = scan.nextInt();
        } else if (numPlayers > GAME_MAX_PLAYERS) {
            display(TOO_MANY_PLAYERS_WARN);
            numPlayers = scan.nextInt();
        }

        return numPlayers;
    }

    /**
     * @param treeHugger
     */
    private static void startPlaying(TreeHugger treeHugger) {
        int gameRound = FIRST_ROUND;

        HashMap<Integer, Zone> zones = populateZones();
        Board treeHuggerBoard = new Board(zones);

        while (treeHugger.isPlayingGame()) {

            treeHugger.setGameRound(gameRound);

            display("\nRound " + gameRound);

            checkAllPlayersBalance(players, treeHugger);

            for (Player player : players) {

                treeHugger.setCurrentPlayer(player);

                display("\nWould " + player.getPlayerName() + " like to take their turn now? Y/N or Q to quit the game!");

                String playerTurnChoice = determinePlayerChoice();

                if(playerTurnChoice.equalsIgnoreCase(PLAYER_CONFIRMATION)){
                    takeTurn(player, treeHuggerBoard, zones, treeHugger);
                } else if (playerTurnChoice.equalsIgnoreCase(PLAYER_QUIT)){
                    endGame(players, treeHugger);
                    break;
                }

            }
            gameRound++;
        }
    }

    /**
     *
     * @param players
     * @param treeHugger
     */
    private static void checkAllPlayersBalance(List<Player> players, TreeHugger treeHugger){

        for(Player player : players){
            if(player.getPlayerRECBalance() <= 0){
                endGame(players, treeHugger);
            }
        }
    }

    /**
     *
     * @param players
     * @param treeHugger
     */
    private static void endGame(List<Player> players, TreeHugger treeHugger){
        List<Integer> scores = new ArrayList<>();

        for (Player player : players){
            scores.add(player.getPlayerRECBalance());
        }

        Collections.reverse(scores);

        if(!checkScoreDuplicates(scores)){

            Collections.sort(players, Collections.reverseOrder());

            display("Scoreboard :");

            for(Player player : players){
                display("Player : " + player.getPlayerName() + " with balance " + player.getPlayerRECBalance());
            }

            display("Congratulations " + players.get(0).getPlayerName() + " on winning the game!");
        } else {
            display("No Winner!");
        }

        display("GAME OVER!");

        treeHugger.setPlayingGame(GAME_IS_NOT_PLAYING);
    }

    /**
     *
     * @param scores
     * @return
     */
    private static boolean checkScoreDuplicates(List<Integer> scores){
        Set<Integer> scoreSet = new HashSet<>(scores);

        return scoreSet.size() < scores.size();

    }

    /**
     *
     * @return
     */
    private static String determinePlayerChoice(){

        String playerChoice = scan.next();

        while (!validatePlayerChoice(playerChoice)) {

            if (playerChoice.toUpperCase(Locale.ROOT).charAt(0) == 'Y') {
                playerChoice = PLAYER_CONFIRMATION;
            } else if (playerChoice.toUpperCase(Locale.ROOT).charAt(0) == 'N') {
                playerChoice = PLAYER_DECLINE;
            }else if (playerChoice.toUpperCase(Locale.ROOT).charAt(0) == 'Q') {
                playerChoice = PLAYER_QUIT;
            } else {
                display("Invalid character selection. Would you like to continue? Press Y for yes or N for no or Q to quite the game!");
                playerChoice = scan.next();
            }
        }

        return playerChoice;
    }

    /**
     *
     * @param playerChoice
     * @return
     */
    private static boolean validatePlayerChoice(String playerChoice){
        return (playerChoice.toUpperCase(Locale.ROOT).charAt(0) == 'Y')
                || (playerChoice.toUpperCase(Locale.ROOT).charAt(0) == 'N')
                || (playerChoice.toUpperCase(Locale.ROOT).charAt(0) == 'Q');
    }

    /**
     *
     * @param player
     * @param board
     * @param zones
     * @param treeHugger
     */
    private static void takeTurn(Player player, Board board, HashMap<Integer, Zone> zones, TreeHugger treeHugger){

        checkPlayerUpgradeToMajorDevelopment(player);

        takePlayerMove(player, board);

        if(isPlayerOnPlayableZone(player)){
            PlayableZone playableZone = (PlayableZone) zones.get(player.getPlayerPosition());

            takePlayableZoneTurn(player, playableZone, treeHugger);

        } else {
            takeNonPlayableZoneTurn(player);
        }

    }

    private static void checkPlayerUpgradeToMajorDevelopment(Player player){
        List<PlayableZone> ownedZones = determineMajorUpgradeableZones(player);

        if(!ownedZones.isEmpty()){
            display("Choose zone to upgrade to Major Development : \n");

            for(PlayableZone zone : ownedZones){
                display("You can upgrade " + zone.getZoneName()
                        + " to a major development.\n Would you like to ? (Y/N)");
                String playerChoice = determinePlayerChoice();

                if(playerChoice.equalsIgnoreCase(PLAYER_CONFIRMATION)
                        && playerHasSufficientFunds(player.getPlayerRECBalance(), zone.getUpgradedCost())){
                    developZone(zone.getLevel(), zone, player);
                    break;

                }
            }

        }
    }

    private static List<PlayableZone> determineMajorUpgradeableZones(Player player){

        List<PlayableZone> ownedZones = player.getOwnedZones();
        List<PlayableZone> upgradeableZones = new ArrayList<>();

        for(PlayableZone zone : ownedZones){
            if(zone.getLevel() == DEVELOPMENT_THREE){
                upgradeableZones.add(zone);
            }
        }

        return upgradeableZones;
    }

    /**
     *
     * @param player
     * @param board
     */
    private static void takePlayerMove(Player player, Board board){
        int playerPositionBeforeMove = player.getPlayerPosition();
        display(player.getPlayerName() + " Current position : " + player.getPlayerPosition());

        int diceRollValue = Dice.rollDice();
        display(player.getPlayerName() + " has rolled " + diceRollValue +" with the dice!");

        int newPlayerPosition = player.movePlayer(diceRollValue);

        display(player.getPlayerName() + " you have landed on " + newPlayerPosition
        + " : " + board.getZone(newPlayerPosition).getZoneName() + ". In the " + board.getZone(newPlayerPosition).getArea() + " Area.");

        if (hasPlayerPassedGo(playerPositionBeforeMove, diceRollValue)) {
            display(player.getPlayerName() + " has just passed go and collected 200 RECS!");
            increasePlayerRecsBalance(player, PASS_G8_SUMMIT_REWARD);
            display(player.getPlayerName() + " new balance is " + player.getPlayerRECBalance());
        }
    }

    /**
     *
     * @param player
     * @param playableZone
     * @param treeHugger
     */
    private static void takePlayableZoneTurn(Player player, PlayableZone playableZone, TreeHugger treeHugger){

        if(playableZone.isOwned()){
            takeOwnedPlayableZoneTurn(playableZone, player, treeHugger);
        } else {
            takeNonOwnedPlayableZoneTurn(playableZone, player, treeHugger);
        }
    }

    /**
     *
     * @param playableZone
     * @param player
     */
    private static void takeOwnedPlayableZoneTurn(PlayableZone playableZone, Player player, TreeHugger treeHugger){
        Player owner = playableZone.getOwner();

        if(player.equals(owner)){
            takeOwnedPlayableZoneTurnAsOwner(playableZone, owner);

        } else {
            takeOwnedPlayableZoneAsNonOwner(playableZone, owner, player, treeHugger);
        }

        endTurn(player);
    }

    /**
     *
     * @param playableZone
     * @param owner
     */
    private static void takeOwnedPlayableZoneTurnAsOwner(PlayableZone playableZone, Player owner){
        Level level = playableZone.getLevel();

        display("You own this zone at level " + level + "!");

        if(!level.equals(MAJOR_DEVELOPMENT) && checkPlayerOwnsAllAreaZones(playableZone.getArea(), owner)){
            display("You own all Zones in this area.\nWould you like to develop this zone to the next level? (Y/N) : ");

            String playerChoice = determinePlayerChoice();

            int upgradeCost = level.equals(DEVELOPMENT_THREE) ? playableZone.getUpgradedCost() : playableZone.getStandardCost();

            if(playerChoice.equalsIgnoreCase(PLAYER_CONFIRMATION)
                    && playerHasSufficientFunds(owner.getPlayerRECBalance(), upgradeCost)){
                developZone(level, playableZone, owner);

            }
        }
    }

    private static boolean checkPlayerOwnsAllAreaZones(Area area, Player player){
        List<PlayableZone> playerZones = player.getOwnedZones();

        int zone = 0;

        for(int i=0;  i<playerZones.size(); i++){
            if(playerZones.get(i).getArea().equals(area)){
                zone++;
            }
        }

        if (area.equals(OCEAN) || area.equals(DESERT)){
            return zone == LARGE_AREA_SIZE;
        } else if (area.equals(FOREST) || area.equals(URBAN)){
            return zone == SMALL_AREA_SIZE;
        } else {
            return false;
        }
    }

    /**
     *
     * @param level
     * @param playableZone
     * @param owner
     */
    private static void  developZone(Level level, PlayableZone playableZone, Player owner){
        Level nextLevel = getNextLevel(level);
        playableZone.setLevel(nextLevel);

        int cost = determineZoneCost(playableZone);
        decreasePlayerRecsBalance(owner, cost);

        display("Zone developed to : " + nextLevel + " at a cost of " + cost +
                " New balance is " + owner.getPlayerRECBalance());
    }

    /**
     *
     * @param playableZone
     * @param owner
     * @param player
     */
    private static void takeOwnedPlayableZoneAsNonOwner(PlayableZone playableZone, Player owner, Player player, TreeHugger treeHugger) {
        int fee = determineZoneFee(playableZone);
        display("Owned by : " + owner.getPlayerName() + " You must pay them : " + fee);

        if(fee > player.getPlayerRECBalance()){
            display(player.getPlayerName() + " does not have sufficient funds to pay "
                    + fee + "to owner " + owner.getPlayerName());

            endGame(players, treeHugger);
        }

        increasePlayerRecsBalance(owner, fee);
        decreasePlayerRecsBalance(player, fee);
    }

    /**
     *
     * @param playableZone
     * @param player
     * @param treeHugger
     */
    private static void takeNonOwnedPlayableZoneTurn(PlayableZone playableZone, Player player, TreeHugger treeHugger){
        display("\nArea is not yet owned!");

        int initialCostToBuyZone = playableZone.getInitialCost();
        int playerBalance = player.getPlayerRECBalance();

        display("Your balance is " + playerBalance);
        display(("This area costs " + initialCostToBuyZone + " to buy."));

        display("\nWould you like to purchase?");
        String playerChoice = determinePlayerChoice();

        if (playerChoice.equalsIgnoreCase(PLAYER_CONFIRMATION) && playerHasSufficientFunds(playerBalance, initialCostToBuyZone)) {
            buyZone(player, playableZone, initialCostToBuyZone);
            endTurn(player);

        } else if ((playerChoice.equalsIgnoreCase(PLAYER_DECLINE))) {
            offerZone(player, initialCostToBuyZone, playableZone, treeHugger);
        } else if (!playerHasSufficientFunds(playerBalance, initialCostToBuyZone)) {
            display("Insufficient Funds!");
            offerZone(player, initialCostToBuyZone, playableZone, treeHugger);
        } else if (playerChoice.equalsIgnoreCase(PLAYER_QUIT)){
            endGame(players, treeHugger);
        }
    }

    /**
     *
     * @param player
     * @param initialCostToBuyZone
     * @param playableZone
     * @param treeHugger
     */
    private static void offerZone(Player player, int initialCostToBuyZone, PlayableZone playableZone, TreeHugger treeHugger) {
        display("Would another player like to buy this? (Y/N)");

        String playerOfferChoice = determinePlayerChoice();

        if (playerOfferChoice.equalsIgnoreCase(PLAYER_CONFIRMATION)) {

            display("Which player would you like to offer this to?");

            List<Integer> validPlayersForOffer = determineValidPlayersForOffer(player);

            int playerChosenForForOffer = determineSelectedPlayer(validPlayersForOffer);
            Player offeredPlayer = players.get(playerChosenForForOffer);

            if(playerHasSufficientFunds(offeredPlayer.getPlayerRECBalance(), initialCostToBuyZone)){

                display(offeredPlayer.getPlayerName()
                        + " you have been selected to buy "
                        + playableZone.getZoneName() + " at board position "
                        + player.getPlayerPosition());

                display("\nWould you like to purchase the offered zone? (Y/N)");

                String playerChoice =determinePlayerChoice();

                if(playerChoice.equalsIgnoreCase(PLAYER_CONFIRMATION)){
                    buyZone(offeredPlayer, playableZone, initialCostToBuyZone);

                } else {
                    display("You have chosen not to buy the offered zone.");
                }
            }

        } else if (playerOfferChoice.equalsIgnoreCase(PLAYER_DECLINE)) {
            endTurn(player);
        } else if (playerOfferChoice.equalsIgnoreCase(PLAYER_QUIT)){
            endGame(players, treeHugger);
        }
    }

    /**
     * Create list of other players,who are not the current player to offer property too
     * when current player chooses not to buy it.
     *
     * @param player Player object to check it is not current Player.
     * @return List of integers of valid player numbers.
     */
    private static List<Integer> determineValidPlayersForOffer(Player player){
        List<Integer> validPlayersForOffer = new ArrayList<>();

        for(Player otherPlayer : players){
            if(!otherPlayer.getPlayerName().equalsIgnoreCase(player.getPlayerName())){
                validPlayersForOffer.add(players.indexOf(otherPlayer));
            }
        }

        return validPlayersForOffer;
    }

    /**
     *
     * @param player
     */
    private static void endTurn(Player player){
        display( player.getPlayerName()
                + " is at position " + player.getPlayerPosition() + " with a balance of " + player.getPlayerRECBalance());
    }

    /**
     *
     * @param level
     * @return
     */
    private static Level getNextLevel(Level level){
        switch (level){
            case UNDEVELOPED :
                return  DEVELOPMENT_ONE;
            case DEVELOPMENT_ONE :
                return  DEVELOPMENT_TWO;
            case DEVELOPMENT_TWO :
                return  DEVELOPMENT_THREE;
            default:
                return MAJOR_DEVELOPMENT;
        }
    }

    /**
     *
     * @param balance
     * @param cost
     * @return
     */
    private static boolean playerHasSufficientFunds(int balance, int cost){
        return (balance - cost) > 0;
    }

    /**
     *
     * @param player
     * @param playableZone
     * @param cost
     */
    private static void buyZone(Player player, PlayableZone playableZone, int cost){
        decreasePlayerRecsBalance(player, cost);
        playableZone.setOwned(IS_OWNED);
        playableZone.setOwner(player);
        player.addNewOwnedZone(playableZone);

        display("Congratulations! You now own " + playableZone.getZoneName() + " at board position " + player.getPlayerPosition());
    }

    /**
     *
     * @param validPlayersForOffer
     * @return
     */
    private static int determineSelectedPlayer(List<Integer> validPlayersForOffer){

        for(int player : validPlayersForOffer){
            display( "Select Player : " + player);
        }

        int playerChosenForForOffer = scan.nextInt();

        while (!validPlayersForOffer.contains(playerChosenForForOffer)) {
            display("Please try again!");
            playerChosenForForOffer = scan.nextInt();
            scan.nextLine();
        }

        return playerChosenForForOffer;

    }

    /**
     *
     * @param player
     */
    private static void takeNonPlayableZoneTurn(Player player){
        display("This area can not be purchased.");
    }

    /**
     *
     * @param playerPositionBeforeMove
     * @param diceRollValue
     * @return
     */
    private static boolean hasPlayerPassedGo(int playerPositionBeforeMove, int diceRollValue) {
        return (playerPositionBeforeMove + diceRollValue) > BOARD_POSITION_12;
    }

    /**
     * @param playerToAddRecs
     * @param addToBalance
     */
    private static void increasePlayerRecsBalance(Player playerToAddRecs, int addToBalance) {
        int updatedBalance = playerToAddRecs.getPlayerRECBalance() + addToBalance;

        playerToAddRecs.setPlayerRECBalance(updatedBalance);
    }

    /**
     * @param playerToAddRecs
     * @param reduceByBalance
     */
    private static void decreasePlayerRecsBalance(Player playerToAddRecs, int reduceByBalance) {
        int updatedBalance = playerToAddRecs.getPlayerRECBalance() - reduceByBalance;

        playerToAddRecs.setPlayerRECBalance(updatedBalance);
    }

    /**
     * @param player
     * @return
     */
    static boolean isPlayerOnPlayableZone(Player player) {
        return player.getPlayerPosition() != BOARD_POSITION_1 &&
                player.getPlayerPosition() != BOARD_POSITION_7;
    }

    /**
     * @return
     */
    private static HashMap<Integer, Zone> populateZones() {

        HashMap<Integer, Zone> zones = new HashMap<>();

        NonPlayableZone go = new NonPlayableZone(G8_SUMMIT, GO);
        NonPlayableZone hugATree = new NonPlayableZone(TAKE_A_BREAK, HUG_A_TREE);
        OceanZone oceanZoneOne = new OceanZone(TIDAL_POWER_GENERATOR_S);
        OceanZone oceanZoneTwo = new OceanZone(TIDAL_POWER_GENERATOR_M);
        OceanZone oceanZoneThree = new OceanZone(TIDAL_POWER_GENERATOR_L);
        DesertZone desertZoneOne = new DesertZone(SOLAR_FARM_S);
        DesertZone desertZoneTwo = new DesertZone(SOLAR_FARM_M);
        DesertZone desertZoneThree = new DesertZone(SOLAR_FARM_L);
        ForestZone forestZoneOne = new ForestZone(FOREST_PLANTATION_S);
        ForestZone forestZoneTwo = new ForestZone(FOREST_PLANTATION_L);
        UrbanZone urbanZoneOne = new UrbanZone(RECYCLING_SITE_S);
        UrbanZone urbanZoneTwo = new UrbanZone(RECYCLING_SITE_L);

        zones.put(BOARD_POSITION_1, go);
        zones.put(BOARD_POSITION_2, oceanZoneOne);
        zones.put(BOARD_POSITION_3, oceanZoneTwo);
        zones.put(BOARD_POSITION_4, oceanZoneThree);
        zones.put(BOARD_POSITION_5, forestZoneOne);
        zones.put(BOARD_POSITION_6, forestZoneTwo);
        zones.put(BOARD_POSITION_7, hugATree);
        zones.put(BOARD_POSITION_8, desertZoneOne);
        zones.put(BOARD_POSITION_9, desertZoneTwo);
        zones.put(BOARD_POSITION_10, desertZoneThree);
        zones.put(BOARD_POSITION_11, urbanZoneOne);
        zones.put(BOARD_POSITION_12, urbanZoneTwo);

        return zones;
    }


    /**
     *
     * @param playableZone
     * @return
     */
    private static int determineZoneFee(PlayableZone playableZone) {

        Level level = playableZone.getLevel();
        int fee;

        switch (level) {
            case DEVELOPMENT_ONE:
                fee = playableZone.getStandardFee();
                break;
            case DEVELOPMENT_TWO:
                fee = playableZone.getStandardFee() * DEVELOPMENT_TWO_FACTOR;
                break;
            case DEVELOPMENT_THREE:
                fee = playableZone.getStandardFee() * DEVELOPMENT_THREE_FACTOR;
                break;
            case MAJOR_DEVELOPMENT:
                fee = playableZone.getUpgradedFee();
                break;
            default:
                fee = playableZone.getBaseFee();
        }

        return fee;
    }

    /**
     *
     * @param playableZone
     * @return
     */
    private static int determineZoneCost(PlayableZone playableZone) {

        Level level = playableZone.getLevel();
        int cost;

        switch (level) {
            case DEVELOPMENT_ONE:
                cost = playableZone.getStandardCost();
                break;
            case DEVELOPMENT_TWO:
                cost = playableZone.getStandardCost() * DEVELOPMENT_TWO_FACTOR;
                break;
            case DEVELOPMENT_THREE:
                cost = playableZone.getStandardCost() * DEVELOPMENT_THREE_FACTOR;
                break;
            case MAJOR_DEVELOPMENT:
                cost = playableZone.getUpgradedCost();
                break;
            default:
                cost = playableZone.getInitialCost();
        }

        return cost;
    }

    /**
     * Display Strings to screen from defined path.
     *
     * @param path Path to values to be output to screen in String format.
     * @throws IOException Thrown when path does not exist.
     */
    private static void displayFromPath(String path) throws IOException {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            line = br.readLine();

            while (line != null) {
                display(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            display("File at " + path + " not found!");
            throw new IOException();
        }
    }

    /**
     * Display Strings to screen.
     *
     * @param stringToDisplay String value to output to screen.
     */
    private static void display(String stringToDisplay) {
        System.out.println(stringToDisplay);
    }
}
