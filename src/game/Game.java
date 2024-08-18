package game;

import item.*;
import players.*;
import shop.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game {
    public static Player player;

    private Scanner scan;
    private int gameMode;
    private int boardHeight;
    private int boardWidth;
    private boolean running;
    private int battleChance;
    private int equipmentChance;
    private String difficulty;

    final int BATTLE_CHANCE_EASY = 65;
    final int BATTLE_CHANCE_MEDIUM = 75;
    final int BATTLE_CHANCE_HARD = 75;
    final int EQUIPMENT_CHANCE_EASY = 20;
    final int EQUIPMENT_CHANCE_MEDIUM = 10;
    final int EQUIPMENT_CHANCE_HARD = 10;

    public Game(){
        scan = new Scanner(System.in);
    }

    /**
     * Handles running a game after one has been created
     **/
    public void runGame() {
        String choice;
        running = true;
        System.out.print("RPG_Game\n\n 1. New Game\n 2. Load Game\n 3. About\n 4. Quit\n > ");
        choice = scan.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }

        switch (choice) {
            case "1":
                chooseGamemode();
                break;
            case "2":
                try{
                    loadPlayer();
                } catch(FileNotFoundException fnf){
                    System.out.println("Load Failed");
                    runGame();
                }
                break;
            case "3":
                System.out.println("The RPG_Game is a game designed by Patrick Reagan primarily for the purpose of improving " +
                        "programming skill through application. Though this started as a group project,\nPatrick is now the only " +
                        "person actively working on the project. Credits to TJ York, Kellen Ferwerda, Paul Oram, and Nathan" +
                        " Short for their contributions early on \nin development. Special thanks to" +
                        " Jeremy Halt for the base inventory and item classes. \nEnter anything to continue");
                scan.nextLine();
                runGame();
                break;
            default:
                System.out.println("See ya next time!.");
                System.exit(0);
                break;
        }
    }

    /**
     * Handles the player choosing a gamemode to play on
     **/
    private void chooseGamemode(){
        String choice;
        int diff = 0;
        System.out.print(	"\n 1. Monster Mash" +
                "\n 2. Adventure" +
                "\n 3. Board Adventure (Work in progress, cannot save or load)" +
                "\n > ");
        choice = scan.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }
        this.gameMode = Integer.parseInt(choice);
        createCharacter();
        setDifficulty(diff);
        System.out.println("As you begin your quest, you've been given a Wooden " + player.getWeaponType() + ". With this weapon" +
                " and clothes on your back, you begin your epic quest.\nStart your adventure by entering '1', access the menu by entering '2'.");

        switch (gameMode) {
            case 2:
                runAdventure();
                break;
            case 3:
                runBoardMode();
                break;
            case 1:
            default:
                runMonsterMash();
                break;
        }
    }

    /**
     * Handles setting the difficulty of a new game
     **/
    private void setDifficulty(int i){
        String choice;

        if (i == 0){
            System.out.print(	"Enter your desired difficulty:\n 1. Easy" +
                    "\n 2. Normal" +
                    "\n 3. Hard" +
                    "\n > ");
            choice = scan.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
                System.out.println("Please enter a valid option");
                choice = scan.nextLine();
            }
            difficulty = choice;
        } else {
            difficulty = Integer.toString(i);
        }

        switch(Integer.parseInt(difficulty)){
            case 1:
                battleChance = BATTLE_CHANCE_EASY;
                equipmentChance = EQUIPMENT_CHANCE_EASY;
                break;
            case 2:
                battleChance = BATTLE_CHANCE_MEDIUM;
                equipmentChance = EQUIPMENT_CHANCE_MEDIUM;
                break;
            case 3:
                battleChance = BATTLE_CHANCE_HARD;
                equipmentChance = EQUIPMENT_CHANCE_HARD;
                break;
        }

    }

    /**
     * Handles the creation of a new character
     **/
    private void createCharacter(){
        String name;
        String choice;

        System.out.print("Enter your characters name: ");
        name = scan.nextLine();

        System.out.print("Please Choose your class:\n 1. Archer\n 2. Knight\n 3. Wizard\n 4. Hunter\n > ");
        choice = scan.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }

        switch (choice) {
            case "1":
                player = new Archer(name);
                break;
            case "3":
                player = new Wizard(name);
                break;
            case "4":
                player = new Hunter(name);
                break;
            case "2":
            default:
                player = new Knight(name);
        }
    }

    /**
     * Handles running the game when in monster mash mode
     **/
    private void runMonsterMash(){
        String choice;

        while(running){
            choice = scan.next();
            while (!choice.equals("1") && !choice.equals("2")){
                System.out.println("Please enter a valid option");
                choice = scan.next();
            }

            if(choice.equals("1")){
                Battle battle = new Battle();
                battle.startBattle(scan, true);
                if (player.getHealthLeft() <= 0) {
                    System.out.print("\n\n\t\tGAME\tOVER\n\n\n");
                    running = false;
                } else {
                    findNewEquipment();
                }
            } else{
                playerMenu();
            }
            if (running){
                System.out.println("Enter '1' to continue your adventure! Enter '2' to go to the menu.");
            }
        }
        scan.nextLine();
        runGame();
    }

    /**
     * Handles running the game when in adventure mode
     **/
    private void runAdventure(){
        while (running){
            String choice;

            while (running){
                choice = scan.next();
                while (!choice.equals("1") && !choice.equals("2")){
                    System.out.println("Please enter a valid option");
                    choice = scan.next();
                }

                if(choice.equals("1")){
                    int g = getChance();
                    if (g <= battleChance){
                        Battle battle = new Battle();
                        battle.startBattle(scan, true);
                        if (player.getHealthLeft() > 0) {
                            findNewEquipment();
                        }
                    } else {
                        getEvent();
                    }
                    if (player.getHealthLeft() <= 0) {
                        System.out.print("\n\n\t\tGAME\tOVER\n\n\n");
                        running = false;
                    }
                } else {
                    playerMenu();
                }
                if (running){
                    System.out.println("Enter '1' to continue your adventure! Enter '2' to go to the menu.");
                }
            }
            scan.nextLine();
            runGame();
        }
    }

    /**
     * Handles running the game when in board mode
     **/
    private void runBoardMode(){
        boardHeight = 9;
        boardWidth = 9;
        Board board = new Board(0, boardHeight,boardWidth );
        board.generateNewBoard();

        board.setCurPosX(0);
        board.setCurPosY(4);
        board.setClear();
        board.setEnemy();
        board.setHidItem();
        board.setExplored();
        board.displayBoard();

        board.getTile(0,4).setType("Home");

        String choice;

        while(running){
            choice = scan.next();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")){
                System.out.println("Please enter a valid option");
                choice = scan.next();
            }

            switch (choice) {
                case "1":
                    movePositions(board);
                    break;
                case "2":
                    playerMenu();
                    break;
                case "3":
                    if (board.getCurrentTile().hidItem && !board.getCurrentTile().shop) {
                        player.getInventory().addRandomItem();
                        board.setHidItem();
                        board.setClear();
                    } else if (board.getCurrentTile().shop) {
                        System.out.println("Upon examining the village, you discover a shop.");
                        Shop shop = new LocalShop();
                        shop.shop();
                        board.setClear();
                    } else {
                        System.out.println("You examined the " + board.getCurrentTile().type + " but didn't find anything.");
                        board.setClear();
                    }
                    break;
                default:
                    board.displayBoard();
                    break;
            }
            if (running){
                System.out.println("Current Location: " + board.getCurrentTile().type + "\nEnter '1' to continue your adventure! Enter '2' to go to the menu. Enter '3' to examine" +
                        " the area. Enter '4' to look at the map.");
            }
        }
    }

    /**
     * Handles moving the player to a new tile in board mode
     **/
    private void movePositions(Board board){
        System.out.println("Where would you like to move to? Enter -1 to go back.");
        String choice;
        boolean complete;

        //still need to be changed to use 1 - board sizes
        if (board.getCurPosY() != 0){
            System.out.println("'W' to move up a tile.");
        }
        if (board.getCurPosY() != boardHeight - 1){
            System.out.println("'S' to move down a tile.");
        }
        if (board.getCurPosX() != 0){
            System.out.println("'A' to move left a tile.");
        }
        if (board.getCurPosX() != boardWidth - 1){
            System.out.println("'D' to move right a tile.");
        }

        choice = scan.next();
        if (choice.equalsIgnoreCase("W") && board.getCurPosY() != 0){
            board.setCurPosY(board.getCurPosY() - 1);
        } else if (choice.equalsIgnoreCase("S") && board.getCurPosY() != boardHeight - 1){
            board.setCurPosY(board.getCurPosY() + 1);
        } else if (choice.equalsIgnoreCase("A") && board.getCurPosX() != 0){
            board.setCurPosX(board.getCurPosX() - 1);
        } else if (choice.equalsIgnoreCase("D") && board.getCurPosX() != boardWidth - 1){
            board.setCurPosX(board.getCurPosX() + 1);
        } else if (!choice.equals("-1")) {
            System.out.println("Invalid input entered");
            movePositions(board);
        }

        if (!choice.equals("-1")){
            board.setExplored();

            if (board.getCurrentTile().type.equals("Dungeon") && board.getCurrentTile().enemy){
                complete = runDungeon();
                if (complete){
                    board.setEnemy();
                    if (!board.getCurrentTile().hidItem){
                        board.setClear();
                    }
                }
            } else if (board.getCurrentTile().enemy){
                Battle battle = new Battle();
                battle.startBattle(scan, true);
                if (player.getHealthLeft() > 0) {
                    findNewEquipment();
                    board.setEnemy();
                    if (!board.getCurrentTile().hidItem){
                        board.setClear();
                    }
                }
            }
            if (player.getHealthLeft() <= 0) {
                System.out.print("\n\n\t\tGAME\tOVER\n\n\n");
                running = false;
            }
        }

        if (board.getCurrentTile().shop){
            System.out.println("You found a village!");
        }
    }

    /**
     * Handles running dungeons
     **/
    private boolean runDungeon(){
        String s;
        boolean complete = false;
        System.out.println("You have stumbled upon a dungeon, do you wish to proceed in search of treasure? Enter '1' for yes or '2' for no." +
                "\n(You will need to fight at least 3 - 5 consecutive battles to beat the dungeon).");
        s = scan.next();
        while (!complete){
            if (s.equals("1")){
                System.out.println("You have chosen to enter the dungeon");
                complete = true;
                String dun;
                double l = 3 + Math.random() * (5 - 3);
                int j = (int) Math.round(l);
                for (int i = 0; i < j; i++) {
                    Battle battle = new Battle();
                    battle.startBattle(scan, false);
                    if (player.getHealthLeft() <= 0) {
                        System.out.print("You have failed the dungeon and lost.");
                        return false;
                    }
                    if (i + 1 != j) {
                        findNewEquipment();
                        System.out.println("Enter '1' to continue the dungeon.");
                        dun = scan.next();
                        while (!dun.equals("1")) {
                            System.out.println("Enter '1' to continue the dungeon.");
                            dun = scan.next();
                        }
                    } else {
                        //boss fights would occur here
                        System.out.println("You have completed the dungeon and found some Ultimate " +
                                "Cheesy Garlic Bread at the end of it.");
                        player.getInventory().addNewItem(new UltimateCheesyGarlicBread(1));
                        return true;
                    }

                }
            } else if (s.equals("2")){
                System.out.println("You have chosen not to enter the dungeon");
                complete = true;
            } else {
                System.out.println("Invalid input entered. Please enter '1' for yes or '2' for no.");
                s = scan.next();
            }
        }

        return false;
    }

    /**
     * Handles running the game when in custom mode
     **/
    private void runCustomMode(){
        //A mode that allows the user to tweak certain aspects of the game such as chance to find events, new equipment,
        //ect
        //To-Do: Implement
    }

    /**
     * Handles displaying the player menu when not within a battle
     **/
    private void playerMenu(){
        boolean menu;
        String choice;
        menu = true;

        while (menu){
            System.out.print("\nMenu:\n" +
                    " 1. Continue\n" +
                    " 2. Inventory\n" +
                    " 3. Stats\n" +
                    " 4. Restart\n" +
                    " 5. Save\n" +
                    " 6. Quit\n" +
                    " > "
            );
            choice = scan.next();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6")){
                System.out.println("Please enter a valid option");
                choice = scan.next();
            }

            switch (choice) {
                case "1":
                    menu = false;
                    break;
                case "2":
                    player.getInventory().useItemsOutside(player);
                    break;
                case "3":
                    String i;

                    System.out.print("Name: " + player.getName() + " Level: " + player.getLevel() + "\nHealth: " + player.getHealthLeft() + "/" + player.getHealth() + " (+" + player.getCanister().getHealthBoost() + " from " + player.getCanister().getItemName() + ")\nAttack: "
                            + player.getAttack() + " (+" + player.getEquipped().getDamage() + " from " + player.getEquipped().getItemName() + ")\nDefense: " + player.getDefense() + " (+" + player.getWorn().getProtection() + " from " + player.getWorn().getItemName() + ")\nSpeed: " + player.getSpeed() + "\nMana: " + player.getManaLeft()
                            + "/" + player.getMana() + "\nExperience: " + player.getExp() + "/" + 100 * player.getLevel() + " Gold: " + player.getGold());
                    if (player.hasPet) {
                        System.out.print("\n\nName: " + player.getCurrentPet().getNickname() + "\nType: " + player.getCurrentPet().getName() +
                                "Level: " + player.getCurrentPet().getLevel() + "\nHealth: " + player.getCurrentPet().getHealthLeft() + "/" + player.getCurrentPet().getHealth()
                                + "\nAttack: " + player.getCurrentPet().getAttack() + "\nDefense: " + player.getCurrentPet().getDefense() +
                                "\nSpeed: " + player.getCurrentPet().getSpeed() + "\nExperience: " + player.getCurrentPet().getExp() + "/" + 100 * player.getCurrentPet().getLevel());
                    }

                    System.out.println("\nEnter -1 to go back");

                    i = scan.next();
                    while (!i.equals("-1")) {
                        System.out.println("Please enter a valid number!");
                        i = scan.next();
                    }
                    break;
                case "4":
                    menu = false;
                    running = false;
                    break;
                case "5":
                    try {
                        savePlayer();
                    } catch (FileNotFoundException fnf) {
                        System.out.println("Save failed");
                    }
                    break;
                default:
                    System.out.println("See ya next time!.");
                    running = false;
                    System.exit(0);
            }
        }
    }

    /**
     * Loads a player using a specified file
     **/
    private void loadPlayer() throws FileNotFoundException{
        System.out.println("Enter the file name with your character in it.");
        String choice = scan.next();

        String playerName;
        int classType, gold, exp, equipped, worn, canister, itemID, quantity, itemCount, currentHealth, currentMana, difficulty;
        double level;
        File inputFile = new File(choice);
        Scanner in = new Scanner(inputFile);

        playerName = in.nextLine();
        level = in.nextDouble();
        classType = in.nextInt();
        gold = in.nextInt();
        gameMode = in.nextInt();
        difficulty = in.nextInt();
        setDifficulty(difficulty);

        exp = in.nextInt();
        currentHealth = in.nextInt();
        currentMana = in.nextInt();
        equipped = in.nextInt();
        worn = in.nextInt();
        canister = in.nextInt();

        if (classType == 1){
            player = new Archer(playerName, level, gold, exp, equipped, worn, canister, currentHealth, currentMana);
        } else if (classType == 2){
            player = new Knight(playerName, level, gold, exp, equipped, worn, canister, currentHealth, currentMana);
        } else if (classType == 3){
            player = new Wizard(playerName, level, gold, exp, equipped, worn, canister, currentHealth, currentMana);
        } else if (classType == 4){
            player = new Hunter(playerName, level, gold, exp, equipped, worn, canister, currentHealth, currentMana);
        } else {
            throw new FileNotFoundException();
        }

        while(in.hasNextInt()){
            itemCount = 0;
            itemID = in.nextInt();
            quantity = in.nextInt();
            while (itemCount < quantity){
                player.getInventory().addNewItem(itemID, player.getWeaponType());
                itemCount++;
            }
        }

        in.close();

        System.out.println("Enter '1' to continue your adventure! Enter '2' to go to the menu.");
        if (gameMode == 1){
            runMonsterMash();
        } else if (gameMode == 2) {
            runAdventure();
        } else if  (gameMode == 3){
            runBoardMode();
        }
    }

    /**
     * Saves a player to a specified file
     **/
    private void savePlayer() throws FileNotFoundException{
        System.out.println("Enter the file name for this save.");
        String choice = scan.next();

        PrintWriter output = new PrintWriter(choice);
        output.print(player.getName() + "\n" + player.getLevel() + "\n" + player.getClassType() + "\n" + player.getGold() + "\n" + gameMode
        + "\n" + difficulty + "\n" + player.getExp() + "\n" + player.getHealthLeft() + "\n" + player.getManaLeft() + "\n" + player.getEquipped().getID()
                + "\n" + player.getWorn().getID() + "\n"+ player.getCanister().getID() + "\n");
        for(int i = 0; i < player.getInventory().itemList.size(); i++){
            output.print(player.getInventory().itemList.get(i).getID() + " " + player.getInventory().itemList.get(i).getStack() + "\n");
        }
        output.flush();
        output.close();
        System.out.println("The game has been saved.");
    }

    /**
     * Used for generating random values
     **/
    private int getChance(){
        double chance;
        int i;

        chance = Math.random() * 100;
        i = (int) Math.round(chance);

        return i;
    }

    /** Gets a random event during play
     */
    private void getEvent(){
        double l = 1 + Math.random() * (9 - 1);
        int j = (int) Math.round(l);

        switch (j){
            case 1:
                System.out.println("You found a small village! In the village you find a local shop.");
                Shop shop = new LocalShop();
                shop.shop();
                break;
            case 2:
                System.out.println("You found a forest!");
                player.getInventory().addRandomItem();
                break;
            case 3:
                System.out.println("You found a cave!");
                player.getInventory().addRandomItem();
                break;
            case 4:
                System.out.println("You found a river!");
                player.getInventory().addRandomItem();
                break;
            case 5:
                System.out.println("You found a roadside inn!\n Your health and mana were fully restored after a nights rest.");
                player.setHealthLeft(player.getHealth());
                player.setManaLeft(player.getMana());
                break;
            case 6:
                System.out.println("You found a lake!");
                player.getInventory().addRandomItem();
                break;
            case 7:
                if (player.getHealthLeft() > 0){
                    System.out.println("You fell into a pit of spikes someone set. Luckily, it was not fatal, but you took 5 damage");
                } else {
                    System.out.println("You fell into a pit of spikes someone set. Unfortunately, it was fatal.");
                }
                player.setHealthLeft(player.getHealthLeft() - 5);
                break;
            case 8:
                runDungeon();
                break;
            case 9:
                System.out.println("It began to rain garlic bread, and you managed to snag some out of the air before it hit the ground!");
                double k = 1 + Math.random() * (2);
                int f = (int) Math.round(k);
                player.getInventory().addNewItem(new GarlicBread(f));
                break;
        }
    }

    /**
     * Randomly generates equipment items when needed
     **/
    private void findNewEquipment(){
        int type = 0;
        if (getChance() < equipmentChance){
            type = 1;
        } else if (getChance() < equipmentChance){
            type = 2;
        } else if (getChance() < equipmentChance){
            type = 3;
        }
        if (type != 0) {
            int amount, id;
            if (player.getLevel() < 10) {
                amount = 1;
            } else if (player.getLevel() < 25) {
                amount = 3;
            } else if (player.getLevel() < 50) {
                amount = 5;
            } else {
                amount = 6;
            }
            id = idGenerator(amount);
            if (type == 1){
                Item item = weaponList()[id];
                System.out.println("You found a " + item.getItemName());
                player.getInventory().addNewItem(item);
            } else if (type == 2) {
                Item item = armorList()[id];
                System.out.println("You found a " + item.getItemName());
                player.getInventory().addNewItem(item);
            } else {
                Item item = canisterList()[id];
                System.out.println("You found a " + item.getItemName());
                player.getInventory().addNewItem(item);
            }

        }
    }

    /**
     * A list of all weapons in the game
     **/
    private Weapon[] weaponList(){
        return new Weapon[]{new Travelers(1,player.getWeaponType()), new Standard(1,player.getWeaponType()), new Soldiers(1,player.getWeaponType()), new Warriors(1,player.getWeaponType()), new Guardians(1,player.getWeaponType()), new Heros(1,player.getWeaponType()), new Legends(1,player.getWeaponType())};
    }

    /**
     * A list of all armors in the game
     **/
    private Armor[] armorList(){
        return new Armor[]{new LeatherChaps(1), new Chainmail(1), new IronPlatemail(1), new SilverPlatemail(1), new TitaniumPlatemail(1), new SteelPlatemail(1), new DragonScalePlatemail(1)};
    }

    /**
     * A list of all heart canisters in the game
     **/
    private HeartCanister[] canisterList(){
        return new HeartCanister[]{new RedHeartCanister(1), new YellowHeartCanister(1), new OrangeHeartCanister(1), new GreenHeartCanister(1), new BlueHeartCanister(1), new PurpleHeartCanister(1), new WhiteHeartCanister(1), new CrystalHeartCanister(1)};
    }

    /**
     * Used for generating id values of a given amount
     * @param amount
     **/
    private static int idGenerator(int amount){
        double a;
        int b;

        a = Math.random() * (amount);
        b = (int) Math.round(a);

        return b;
    }
}
