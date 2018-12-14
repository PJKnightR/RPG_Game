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
    private boolean dungeon, running;
    private final int battleChance = 75, equipmentChance = 10;

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
        System.out.print(	"\n 1. Monster Mash" +
                "\n 2. Adventure" +
                "\n > ");
        choice = scan.nextLine();
        while (!choice.equals("1") && !choice.equals("2")){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }
        this.gameMode = Integer.parseInt(choice);
        createCharacter();
        System.out.println("Start your adventure by entering '1', access the menu by entering '2'.");
        if (gameMode == 1){
            runMosterMash();
        } else {
            runAdventure();
        }
    }

    private void createCharacter(){
        String name, choice;
        System.out.print("Enter your characters name: ");
        name = scan.nextLine();

        System.out.print("Please Choose your class:\n 1. Archer\n 2. Knight\n 3. Wizard\n > ");
        choice = scan.nextLine();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
            System.out.println("Please enter a valid option");
            choice = scan.nextLine();
        }

        if (choice.equals("1")){
            this.PC = new Archer(name);
        } else if (choice.equals("2")){
            this.PC = new Knight(name);
        } else if (choice.equals("3")){
            this.PC = new Wizard(name);
        } else {
            this.PC = new Knight(name);
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
                batbat.startBattle(PC, scan);
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
                        batbat.startBattle(PC, scan);
                        if (PC.getHealthLeft() > 0) {
                            findNewEquipment();
                        }
                    } else {
                        dungeon = false;
                        getEvent(PC);
                        if (dungeon) {
                            runDungeon();
                        }
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

    private void runDungeon(){
        String dun;
        double l = 3 + Math.random() * (5 - 3);
        int j = (int) Math.round(l);
        for (int i = 0; i < j; i++) {
            Battle batbat = new Battle();
            batbat.startBattle(PC, scan);
            if (PC.getHealthLeft() <= 0) {
                System.out.print("You have failed the dungeon and lost.");
                break;
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
            }

        }
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
        int classType, gold, exp, equipped, worn, itemID, quantity, itemCount, currentHealth, currentMana;
        double level;
        File inputFile = new File(choice);
        Scanner in = new Scanner(inputFile);

        playerName = in.nextLine();
        level = in.nextDouble();
        classType = in.nextInt();
        gold = in.nextInt();
        gameMode = in.nextInt();
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
        + "\n" + PC.getExp() + "\n" + PC.getHealthLeft() + "\n" + PC.getManaLeft() + "\n" + PC.getEquipped().getID()
                + "\n" + PC.getWorn().getID() + "\n");
        for(int i = 0; i < PC.getInventory().itemList.size(); i++){
            output.print(PC.getInventory().itemList.get(i).getID() + " " + PC.getInventory().itemList.get(i).getStack() + "\n");
        }
        output.flush();
        output.close();
        System.out.println("The game has been saved");
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
                String s;
                boolean complete = false;
                dungeon = false;
                System.out.println("You have stumbled upon a dungeon, do you wish to proceed in search of treasure? Enter '1' for yes or '2' for no." +
                        "\n(You will need to fight at least 3 - 5 consecutive battles to beat the dungeon).");
                s = scan.next();
                while (!complete){
                    dungeon = false;
                    if (s.equals("1")){
                        System.out.println("You have chosen to enter the dungeon");
                        dungeon = true;
                        complete = true;
                    } else if (s.equals("2")){
                        System.out.println("You have chosen not to enter the dungeon");
                        complete = true;
                    } else {
                        System.out.println("Invalid input entered. Please enter '1' for yes or '2' for no.");
                        s = scan.next();
                    }
                }
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
            } else {
                Item item = armorList()[id];
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

    private static int idGenerator(int amount){
        double a;
        int b;

        a = Math.random() * (amount);
        b = (int) Math.round(a);

        return b;
    }
}
