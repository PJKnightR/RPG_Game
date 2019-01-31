package game;

import item.*;
import players.*;
import shop.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game {
    private Scanner scan;
    private int gameMode;
    private Player PC;
    private boolean running;
    private int battleChance, equipmentChance;
    private String difficulty;

    public Game(){
        scan = new Scanner(System.in);
    }

    public void runGame() {
        String choice;
        running = true;
        System.out.print("RPG_Game\n\n 1. New Game\n 2. Load Game\n 3. About\n 4. Quit\n > ");
        choice = scan.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }

        if (choice.equals("1")){
            chooseGamemode();
        } else if (choice.equals("2")){
            try{
                loadPlayer();
            } catch(FileNotFoundException flarg){
                System.out.println("Load Failed");
                runGame();
            }
        } else if (choice.equals("3")){
            System.out.println("This feature is still in development");
            runGame();
        } else {
            System.out.println("See ya next time!.");
            System.exit(0);
        }
    }

    private void chooseGamemode(){
        String choice;
        int diff = 0;
        System.out.print(	"\n 1. Monster Mash" +
                "\n 2. Adventure" +
                "\n 3. Board Adventure (Work in progress, unstable, cannot save or load)" +
                "\n > ");
        choice = scan.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }
        this.gameMode = Integer.parseInt(choice);
        createCharacter();
        setDifficulty(diff);
        System.out.println("As you begin your quest, you've been given a Wooden " + PC.getWeaponType() + ". With this weapon" +
                " and clothes on your back, you begin your epic quest.\nStart your adventure by entering '1', access the menu by entering '2'.");
        if (gameMode == 1){
            runMosterMash();
        } else if (gameMode == 2 ){
            runAdventure();
        } else {
            runBoardMode();
        }
    }

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
                battleChance = 65;
                equipmentChance = 20;
                break;
            case 2:
                battleChance = 75;
                equipmentChance = 10;
                break;
            case 3:
                battleChance = 75;
                equipmentChance = 10;
                break;
        }

    }

    private void createCharacter(){
        String name, choice;
        System.out.print("Enter your characters name: ");
        name = scan.nextLine();

        System.out.print("Please Choose your class:\n 1. Archer\n 2. Knight\n 3. Wizard\n 4. Hunter\n > ");
        choice = scan.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }

        if (name.equalsIgnoreCase("RiskyLungeLiskyRunge")){
            this.PC = new RiskyLungeKLnight(name);
            System.out.println("Welcome back, master.");
        } else {
            if (choice.equals("1")){
                this.PC = new Archer(name);
            } else if (choice.equals("2")){
                this.PC = new Knight(name);
            } else if (choice.equals("3")){
                this.PC = new Wizard(name);
            } else if (choice.equals("4")){
                this.PC = new Hunter(name);
            } else {
                this.PC = new Knight(name);
            }
        }
    }

    private void runMosterMash(){
        String choice;
        int c;

        while(running){
            choice = scan.next();
            while (!choice.equals("1") && !choice.equals("2")){
                System.out.println("Please enter a valid option");
                choice = scan.next();
            }
            c = Integer.parseInt(choice);
            if(c == 1){
                Battle batbat = new Battle();
                batbat.startBattle(PC, scan, true);
                if (PC.getHealthLeft() <= 0) {
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

    private void runAdventure(){
        while(running){
            String choice;
            int c;

            while(running){
                choice = scan.next();
                while (!choice.equals("1") && !choice.equals("2")){
                    System.out.println("Please enter a valid option");
                    choice = scan.next();
                }
                c = Integer.parseInt(choice);
                if(c == 1){
                    int g = getChance();
                    if (g <= battleChance){
                        Battle batbat = new Battle();
                        batbat.startBattle(PC, scan, true);
                        if (PC.getHealthLeft() > 0) {
                            findNewEquipment();
                        }
                    } else {
                        getEvent(PC);
                    }
                    if (PC.getHealthLeft() <= 0) {
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

    private void runBoardMode(){
        Board board = new Board(0, 9,9 );
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
        int c;

        while(running){
            choice = scan.next();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")){
                System.out.println("Please enter a valid option");
                choice = scan.next();
            }
            c = Integer.parseInt(choice);
            if(c == 1){
                movePositions(board);
            } else if (c == 2){
                playerMenu();
            } else if (c == 3) {
                if (board.getCurrentTile().hidItem && !board.getCurrentTile().shop){
                    PC.getInventory().addRandomItem();
                    board.setHidItem();
                    board.setClear();
                } else if (board.getCurrentTile().shop){
                    System.out.println("Upon examining the village, you discover a shop.");
                    Shop shop = new LocalShop();
                    shop.shop(PC);
                    board.setClear();
                } else {
                    System.out.println("You examined the " + board.getCurrentTile().type + " but didn't find anything.");
                    board.setClear();
                }
            } else {
                board.displayBoard();
            }
            if (running){
                System.out.println("Current Location: " + board.getCurrentTile().type + "\nEnter '1' to continue your adventure! Enter '2' to go to the menu. Enter '3' to examine" +
                        " the area. Enter '4' to look at the map.");
            }
        }
    }

    private void movePositions(Board board){
        System.out.println("Where would you like to move to? Enter -1 to go back.");
        String choice;
        boolean complete;

        //still need to be changed to use 1 - board sizes
        if (board.getCurPosY() != 0){
            System.out.println("'W' to move up a tile.");
        }
        if (board.getCurPosY() != 8){
            System.out.println("'S' to move down a tile.");
        }
        if (board.getCurPosX() != 0){
            System.out.println("'A' to move left a tile.");
        }
        if (board.getCurPosX() != 8){
            System.out.println("'D' to move right a tile.");
        }

        choice = scan.next();
        if (choice.equalsIgnoreCase("W") && board.getCurPosY() != 0){
            board.setCurPosY(board.getCurPosY() - 1);
        } else if (choice.equalsIgnoreCase("S") && board.getCurPosY() != 8){
            board.setCurPosY(board.getCurPosY() + 1);
        } else if (choice.equalsIgnoreCase("A") && board.getCurPosX() != 0){
            board.setCurPosX(board.getCurPosX() - 1);
        } else if (choice.equalsIgnoreCase("D") && board.getCurPosX() != 8){
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
                Battle batbat = new Battle();
                batbat.startBattle(PC, scan, true);
                if (PC.getHealthLeft() > 0) {
                    findNewEquipment();
                    board.setEnemy();
                    if (!board.getCurrentTile().hidItem){
                        board.setClear();
                    }
                }
            }
            if (PC.getHealthLeft() <= 0) {
                System.out.print("\n\n\t\tGAME\tOVER\n\n\n");
                running = false;
            }
        }

        if (board.getCurrentTile().shop){
            System.out.println("You found a village!");
        }
    }

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
                    Battle batbat = new Battle();
                    batbat.startBattle(PC, scan, false);
                    if (PC.getHealthLeft() <= 0) {
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
                        System.out.println("You have completed the dungeon and found some Ultimate " +
                                "Cheesy Garlic Bread at the end of it.");
                        PC.getInventory().addNewItem(new UltimateCheesyGarlicBread(1));
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

    private void runCustomMode(){
        //A mode that allows the user to tweak certain aspects of the game such as chance to find events, new equipment,
        //ect
    }

    private void playerMenu(){
        boolean menu;
        String choice;
        int c;
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
            c = Integer.parseInt(choice);

            if (c == 1){
                menu = false;
            } else if (c == 2){
                PC.getInventory().useItemsOutside(PC);
            } else if (c == 3){
                String i;

                System.out.print("Name: " + PC.getName() + " Level: " + PC.getLevel() + "\nHealth: " + PC.getHealthLeft() + "/" + PC.getHealth() + "\nAttack: "
                        + PC.getAttack() + " (+" + PC.getEquipped().getDamage() + " from " + PC.getEquipped().getItemName() + ")\nDefense: " + PC.getDefense() + " (+" + PC.getWorn().getProtection() + " from " + PC.getWorn().getItemName() + ")\nSpeed: " + PC.getSpeed() + "\nMana: " + PC.getManaLeft()
                        + "/" + PC.getMana() + "\nExperience: " + PC.getExp() + "/" + 100 * PC.getLevel() + " Gold: " + PC.getGold() + "\nEnter -1 to go back.\n");

                i = scan.next();
                while (!i.equals("-1")){
                    System.out.println("Please enter a valid number!");
                    i = scan.next();
                }
            } else if (c == 4){
                menu = false;
                running = false;
            } else if (c == 5){
                try{
                    savePlayer();
                } catch (FileNotFoundException blarg){
                    System.out.println("Save failed");
                }
            } else {
                System.out.println("See ya next time!.");
                running = false;
                System.exit(0);
            }
        }
    }

    private void loadPlayer() throws FileNotFoundException{
        System.out.println("Enter the file name with your character in it.");
        String choice = scan.next();

        String playerName;
        int classType, gold, exp, equipped, worn, itemID, quantity, itemCount, currentHealth, currentMana, difficulty;
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


        if (classType == 1){
            this.PC = new Archer(playerName, level, gold, exp, equipped, worn, currentHealth, currentMana);
        } else if (classType == 2){
            this.PC = new Knight(playerName, level, gold, exp, equipped, worn, currentHealth, currentMana);
        } else if (classType == 3){
            this.PC = new Wizard(playerName, level, gold, exp, equipped, worn, currentHealth, currentMana);
        } else if (classType == 4){
            this.PC = new Hunter(playerName, level, gold, exp, equipped, worn, currentHealth, currentMana);
        } else {
            throw new FileNotFoundException();
        }

        while(in.hasNextInt()){
            itemCount = 0;
            itemID = in.nextInt();
            quantity = in.nextInt();
            while (itemCount < quantity){
                PC.getInventory().addNewItem(itemID, PC.getWeaponType());
                itemCount++;
            }
        }

        in.close();

        System.out.println("Enter '1' to continue your adventure! Enter '2' to go to the menu.");
        if (gameMode == 1){
            runMosterMash();
        } else if (gameMode == 2) {
            runAdventure();
        }
    }

    private void savePlayer() throws FileNotFoundException{
        System.out.println("Enter the file name for this save.");
        String choice = scan.next();

        PrintWriter output = new PrintWriter(choice);
        output.print(PC.getName() + "\n" + PC.getLevel() + "\n" + PC.getClassType() + "\n" + PC.getGold() + "\n" + gameMode
        + "\n" + difficulty + "\n" + PC.getExp() + "\n" + PC.getHealthLeft() + "\n" + PC.getManaLeft() + "\n" + PC.getEquipped().getID()
                + "\n" + PC.getWorn().getID() + "\n");
        for(int i = 0; i < PC.getInventory().itemList.size(); i++){
            output.print(PC.getInventory().itemList.get(i).getID() + " " + PC.getInventory().itemList.get(i).getStack() + "\n");
        }
        output.flush();
        output.close();
        System.out.println("The game has been saved.");
    }

    private int getChance(){
        double chance;
        int i;

        chance = Math.random() * 100;
        i = (int) Math.round(chance);

        return i;
    }

    /** Gets a random event during play
     * @param PC the player character
     */
    private void getEvent(Player PC){
        double l = 1 + Math.random() * (9 - 1);
        int j = (int) Math.round(l);

        switch (j){
            case 1:
                System.out.println("You found a small village! In the village you find a local shop.");
                Shop shop = new LocalShop();
                shop.shop(PC);
                break;
            case 2:
                System.out.println("You found a forest!");
                PC.getInventory().addRandomItem();
                break;
            case 3:
                System.out.println("You found a cave!");
                PC.getInventory().addRandomItem();
                break;
            case 4:
                System.out.println("You found a river!");
                PC.getInventory().addRandomItem();
                break;
            case 5:
                System.out.println("You found a roadside inn!\n Your health and mana were fully restored after a nights rest.");
                PC.setHealthLeft(PC.getHealth());
                PC.setManaLeft(PC.getMana());
                break;
            case 6:
                System.out.println("You found a lake!");
                PC.getInventory().addRandomItem();
                break;
            case 7:
                if (PC.getHealthLeft() > 0){
                    System.out.println("You fell into a pit of spikes someone set. Luckily, it was not fatal, but you took 5 damage");
                } else {
                    System.out.println("You fell into a pit of spikes someone set. Unfortunatly, it was fatal.");
                }
                PC.setHealthLeft(PC.getHealthLeft() - 5);
                break;
            case 8:
                runDungeon();
                break;
            case 9:
                System.out.println("It began to rain garlic bread, and you managed to pick some up before it hit the ground!");
                double k = 1 + Math.random() * (2);
                int f = (int) Math.round(k);
                PC.getInventory().addNewItem(new GarlicBread(f));
                break;
        }
    }

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
            if (PC.getLevel() < 10) {
                amount = 1;
            } else if (PC.getLevel() < 25) {
                amount = 3;
            } else if (PC.getLevel() < 50) {
                amount = 5;
            } else {
                amount = 6;
            }
            id = idGenerator(amount);
            if (type == 1){
                Item item = weaponList()[id];
                System.out.println("You found a " + item.getItemName());
                PC.getInventory().addNewItem(item);
            } else if (type == 2) {
                Item item = armorList()[id];
                System.out.println("You found a " + item.getItemName());
                PC.getInventory().addNewItem(item);
            } else {
                Item item = canisterList()[id];
                System.out.println("You found a " + item.getItemName());
                PC.getInventory().addNewItem(item);
            }

        }
    }

    private Weapon[] weaponList(){
        return new Weapon[]{new Travelers(1,PC.getWeaponType()), new Standard(1,PC.getWeaponType()), new Soldiers(1,PC.getWeaponType()), new Warriors(1,PC.getWeaponType()), new Guardians(1,PC.getWeaponType()), new Heros(1,PC.getWeaponType()), new Legends(1,PC.getWeaponType())};
    }

    private Armor[] armorList(){
        return new Armor[]{new LeatherChaps(1), new Chainmail(1), new IronPlatemail(1), new SilverPlatemail(1), new TitaniumPlatemail(1), new SteelPlatemail(1), new DragonScalePlatemail(1)};
    }

    private HeartCanister[] canisterList(){
        return new HeartCanister[]{new RedHeartCanister(1), new YellowHeartCanister(1), new OrangeHeartCanister(1), new GreenHeartCanister(1), new BlueHeartCanister(1), new PurpleHeartCanister(1), new WhiteHeartCanister(1)};
    }

    private static int idGenerator(int amount){
        double a;
        int b;

        a = Math.random() * (amount);
        b = (int) Math.round(a);

        return b;
    }
}
