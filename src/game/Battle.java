package game;

import enemies.*;
import pets.*;
import java.util.Scanner;

public class Battle {
    private Enemy enemy;
    private Pet pet;
    private Scanner scan;
    private int selectedMove;
    private int enemySelectedMove;
    private boolean battling;
    private boolean enemyLoss;
    private boolean petBattle;
    private boolean canRun;
    private boolean isPoisonedPlayer;
    private boolean isPoisonedEnemy;
    private boolean isPetrifiedPlayer;
    private boolean isPetrifiedEnemy;
    private boolean isParylizedPlayer;
    private boolean isParylizedEnemy;

    final int POISON_DAMAGE_CHANCE = 50;
    final int PETRIFIED_CURE_CHANCE = 50;
    final int PARALIZED_CURE_CHANCE = 50;
    final int PARALIZED_MOVE_CHANCE = 50;

    public Battle(){
        isParylizedEnemy = false;
        isParylizedPlayer = false;
        isPetrifiedEnemy = false;
        isPetrifiedPlayer = false;
        isPoisonedEnemy = false;
        isPoisonedPlayer = false;
        battling = true;
        enemyLoss = false;
    }

    /**
     * Initializes values needed to start the battle and declares what type of enemy has appeared
     * @param s input scanner
     */
    public void startBattle(Scanner s, Boolean canRun){
        this.enemy = generateEnemy();
        this.scan = s;
        this.canRun = canRun;
        System.out.println("A " + enemy.getName() + " appeared. Engage the " + enemy.getName() + " " + Game.player.getName() + "!");
        if (getChance() < 10 && !Game.player.hasPet){
            petBattle = true;
            this.pet = generatePet();
            System.out.println("The " + enemy.getName() + " was attacking a " + pet.getName() + ". Save them!");
        }
        doBattle();
    }

    /**
     * Runs the process of performing a battle by calling the move, attack and health methods for both the player and enemy.
     * Compares the speed of the enemy and player. Whoever is faster gets to attack first.
     * Prints out a message declaring a winner at the end of each battle.
     */
    private void doBattle(){
        while (battling && !enemyLoss){
            if (isPoisonedPlayer){
                if (getChance() > POISON_DAMAGE_CHANCE){
                    isPoisonedPlayer = false;
                    System.out.println("You are no longer poisoned");
                } else {
                    Game.player.setHealthLeft(Game.player.getHealthLeft() - (int)((Game.player.getHealth() * .1) + 1));
                    System.out.println("You took " + (int)((Game.player.getHealth() * .1) + 1) + " damage from poison.");
                    battling = checkPlayerStatus();
                    if (!battling){
                        System.out.println("You were defeated by the " + enemy.getName() + "!");
                        break;
                    }
                }
            }

            if (isPoisonedEnemy){
                if (getChance() > POISON_DAMAGE_CHANCE){
                    isPoisonedEnemy = false;
                    System.out.println("The " + enemy.getName() + " is no longer poisoned");
                } else {
                    enemy.setHealthLeft(enemy.getHealthLeft() - (int)((enemy.getHealth() * .1) + 1));
                    System.out.println("The " + enemy.getName() + " took " + (int)((enemy.getHealth() * .1) + 1) + " damage from poison.");
                    battling = checkEnemyStatus();
                    if (!battling){
                        System.out.println("You defeated the " + enemy.getName() + "!");
                        enemyLoss = true;
                        break;
                    }
                }
            }

            if (battling){

                if (isPetrifiedPlayer){
                    if (getChance() > PETRIFIED_CURE_CHANCE){
                        isPetrifiedPlayer = false;
                        System.out.println("You are no longer petrified");
                    }
                }

                if(!isPetrifiedPlayer){
                    playerMove();
                    if (selectedMove == -1){
                        break;
                    }
                    enemyMove();
                }

                if(isParylizedPlayer){
                    if (getChance() > PARALIZED_CURE_CHANCE){
                        isParylizedPlayer = false;
                        System.out.println("You are no longer paralyzed!");
                    }
                }

                if (isPetrifiedEnemy){
                    if (getChance() > PETRIFIED_CURE_CHANCE){
                        isPetrifiedEnemy = false;
                        System.out.println("The " + enemy.getName() + " is no longer petrified!");
                    }
                }

                if(isParylizedEnemy){
                    if (getChance() > PARALIZED_CURE_CHANCE){
                        isParylizedEnemy = false;
                        System.out.println("The " + enemy.getName() + " is no longer paralized");
                    }
                }

                if (Game.player.getSpeed() >= enemy.getSpeed()){
                    if(!isPetrifiedPlayer){
                        if(isParylizedPlayer){
                            if(getChance() > PARALIZED_MOVE_CHANCE){
                                System.out.println("You are paralyzed but managed to move");
                                enemyDamageStep();
                            } else {
                                System.out.println("You are paralyzed and cannot move!");
                            }
                        } else {
                            enemyDamageStep();
                        }
                    }
                    if (isPetrifiedPlayer){
                        System.out.println("You are petrified and cannot move");
                    }
                    if(battling){
                        if(!isPetrifiedEnemy){
                            if(isParylizedEnemy){
                                if(getChance() > PARALIZED_MOVE_CHANCE){
                                    System.out.println("The enemy is paralyzed but managed to attack!");
                                    playerDamageStep();
                                } else {
                                    System.out.println("The enemy is paralyzed and cannot move");
                                }
                            } else {
                                playerDamageStep();
                            }
                        }
                        if (isPetrifiedEnemy){
                            System.out.println("The " + enemy.getName() + " is petrified and cannot move");
                        }
                    }
                } else if (Game.player.getSpeed() < enemy.getSpeed()){
                    if(!isPetrifiedEnemy){
                        if(isParylizedEnemy){
                            if(getChance() > PARALIZED_MOVE_CHANCE){
                                System.out.println("The enemy is paralyzed but managed to attack!");
                                playerDamageStep();
                            } else {
                                System.out.println("The enemy is paralyzed and cannot move");
                            }
                        } else {
                            playerDamageStep();
                        }
                    }
                    if (isPetrifiedEnemy){
                        System.out.println("The " + enemy.getName() + " is petrified and cannot move");
                    }

                    if(battling){
                        if(!isPetrifiedPlayer){
                            if(isParylizedPlayer){
                                if(getChance() > PARALIZED_MOVE_CHANCE){
                                    System.out.println("You are paralyzed but managed to move");
                                    enemyDamageStep();
                                } else {
                                    System.out.println("You are paralyzed and cannot move!");
                                }
                            } else {
                                enemyDamageStep();
                            }
                        }
                        if (isPetrifiedPlayer){
                            System.out.println("You are petrified and cannot move");
                        }
                    }
                }
            }
        }

        if (enemyLoss){
            battleEnd();
        }
    }

    private void playerDamageStep(){
        Game.player.setHealthLeft(Game.player.getHealthLeft() - enemyAttack());
        battling = checkPlayerStatus();
        if (!battling){
            System.out.println("You were defeated by the " + enemy.getName() + "!");
        }
    }

    private void enemyDamageStep(){
        enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack());
        if(Game.player.hasPet){
            if(!Game.player.getCurrentPet().isFainted()){
                enemy.setHealthLeft(enemy.getHealthLeft() - petAttack());
            }
        }
        battling = checkEnemyStatus();
        if (!battling){
            System.out.println("You defeated the " + enemy.getName() + "!");
            enemyLoss = true;
        }
    }

    public void battleEnd(){
        double itemChance;

        if(petBattle){
            Game.player.setPet(pet);
            System.out.println("Upon defeating the " + enemy.getName() + " the " + pet.getName() + " wishes to join you " +
                    "on your quest. Give a nickname to your new " + pet.getName() + "? Enter '1' for yes or -1 for no");
            scan.nextLine();
            String s = scan.nextLine();
            while(!s.equals("1") && !s.equals("-1")){
                System.out.println("Invalid input entered. Enter '1' for yes or '-1' for no");
                s = scan.nextLine();
            }
            if(s.equals("1")){
                System.out.println("Enter nickname for your new " + pet.getName());
                Game.player.getCurrentPet().setNickname(scan.nextLine());
            }
        }

        itemChance = getChance();
        if (itemChance > 50){
            Game.player.getInventory().addRandomItem();
        }
        if (enemy.getDifficulty().equalsIgnoreCase("easy")){
            Game.player.gainExp(50 + (int)getChance());
            Game.player.gainGold(100 + (int)getChance());
        } else if (enemy.getDifficulty().equalsIgnoreCase("moderate")){
            Game.player.gainExp(100 + (int)getChance());
            Game.player.gainGold(150 + (int)getChance());
        } else if (enemy.getDifficulty().equalsIgnoreCase("hard")){
            Game.player.gainExp(250 + (int)getChance());
            Game.player.gainGold(200 + (int)getChance());
        } else {
            Game.player.gainExp(100);
            Game.player.gainGold(100);
        }
        System.out.print("!\n");

        if(Game.player.hasPet){
            Game.player.getCurrentPet().gainExp(50);
            if(Game.player.getCurrentPet().checkLevelUp()){
                Game.player.getCurrentPet().levelUp();
            }
        }

        if(Game.player.checkLevelUp()){
            Game.player.levelUp();
        }
    }

    /**
     * Allows the user to choose their own move whenever an enemy appears. They may choose to fight, use an item, or run away
     */
    public void playerMove(){
        boolean action = false;
        String move;

        displayHealth();
        System.out.println("What would you like to do? (Enter the corresponding number):\n 1. Fight\n 2. Use an item\n 3. View Your Stats\n 4. Run");
        while (!action){
            move = scan.next();
            switch (move) {
                case "1":
                    action = true;
                    selectAttack();
                    break;
                case "2":
                    action = true;
                    this.selectedMove = 0;
                    Game.player.getInventory().useItem(Game.player, this);
                    break;
                case "3":
                    displayPlayerStats();
                    System.out.println("What would you like to do?\n 1. Fight\n 2. Use an item\n 3. View Your Stats\n 4. Run");
                    break;
                case "4":
                    action = true;
                    this.selectedMove = 0;
                    double run = getChance();
                    if (run > 50 && canRun) {
                        System.out.println("You escaped from the " + enemy.getName());
                        this.selectedMove = -1;
                    } else {
                        if (!canRun) {
                            System.out.println("You cannot run from this battle.");
                        } else {
                            System.out.println("The " + enemy.getName() + " blocks your escape path!");
                        }
                    }
                    break;
                default:
                    System.out.println("Please enter a valid move");
                    break;
            }
        }
    }

    /**
     * Prints out all the player's current stats including their name, health, skill level, attack strength
     * defensive strength, speed, mana, and amount of experience.
     */
    private void displayPlayerStats(){
        String choice;

        System.out.print("Name: " + Game.player.getName() + " Level: " + Game.player.getLevel() + "\nHealth: " + Game.player.getHealthLeft() + "/" + Game.player.getHealth() + " (+" + Game.player.getCanister().getHealthBoost() + " from " + Game.player.getCanister().getItemName() + ")\nAttack: "
                + Game.player.getAttack() + " (+" + Game.player.getEquipped().getDamage() + " from " + Game.player.getEquipped().getItemName() + ")\nDefense: " + Game.player.getDefense() + " (+" + Game.player.getWorn().getProtection() + " from " + Game.player.getWorn().getItemName() + ")\nSpeed: " + Game.player.getSpeed() + "\nMana: " + Game.player.getManaLeft()
                + "/" + Game.player.getMana() + "\nExperience: " + Game.player.getExp() + "/" + 100 * Game.player.getLevel() + " Gold: " + Game.player.getGold());
        if(Game.player.hasPet){
            System.out.print("\n\nName: " + Game.player.getCurrentPet().getNickname() + "\nType: " + Game.player.getCurrentPet().getName() +
                    "Level: " + Game.player.getCurrentPet().getLevel() + "\nHealth: " + Game.player.getCurrentPet().getHealthLeft() + "/" + Game.player.getCurrentPet().getHealth()
                    + "\nAttack: " + Game.player.getCurrentPet().getAttack() + "\nDefense: " + Game.player.getCurrentPet().getDefense() +
                    "\nSpeed: " + Game.player.getCurrentPet().getSpeed() + "\nExperience: " + Game.player.getCurrentPet().getExp() + "/" + 100 * Game.player.getCurrentPet().getLevel());
        }

        System.out.println("\nEnter -1 to go back");
        choice = scan.next();
        while (!choice.equals("-1")){
            System.out.println("Please enter a valid number!");
            choice = scan.next();
        }
        displayHealth();
    }

    /**
     * Allows the player to select which attack they would like to use
     */
    private void selectAttack(){
        int attack;
        int r;
        String a;
        boolean done = false;

        for(int i = 0; i < Game.player.att.size(); i++){
            System.out.print(i + 1 + ". " + Game.player.att.get(i).getAttackName() + " (Mana Cost: " + Game.player.att.get(i).getManaCost() + ") ");
        }
        System.out.println("\nEnter -1 to go back");


        while (!done){
            r = 0;
            a = scan.next();
            try
            {
                Integer.parseInt(a);
            } catch (NumberFormatException ex) {
                r = 1;
                System.out.println("Please enter an appropriate move number!");
            }
            if (r == 0){
                attack = Integer.parseInt(a);
                if (attack == -1){
                    done = true;
                    playerMove();
                } else if (attack < -2  || attack > Game.player.att.size() || attack == 0) {
                    System.out.println("Please enter an applicable number!");
                } else if (Game.player.getManaLeft() < Game.player.att.get(attack - 1).getManaCost()){
                    System.out.println("Not enough mana for this attack! Please enter an applicable number!");
                } else {
                    done = true;
                    selectedMove = attack;
                }
            }

        }

    }

    /**
     * Returns a random element from an arrayList of possible enemy moves, based on the enemy type.
     */
    private void enemyMove(){
        double enemyMove = 1 + Math.random() * (enemy.att.size() - 1);
        enemySelectedMove = (int)enemyMove;
    }

    /**
     * Calculates the amount of damage a player's attack does against the opponent.
     * Prints out the type of attack used and how much damage it inflicted.
     * @return the damage done by the player's attack
     */
    private int playerAttack(){
        if (selectedMove == 0){
            return 0;
        } else {
            int att = selectedMove - 1;
            double damage;

            Game.player.setManaLeft(Game.player.getManaLeft() - Game.player.att.get(att).getManaCost());

            damage = (((((2 * Game.player.getLevel() / 5) + 2) * (Game.player.getAttack() / enemy.getDefense()) * (Game.player.att.get(att).getPower())) / 50) + 2) * getCriticalHitModifier();
            System.out.println("You attack using " + Game.player.att.get(att).getAttackName() + ". It did " + (int)damage + " damage.");

            if (Game.player.att.get(att).getPetrifyChance() > 0){
                if (getChance() >= Game.player.att.get(att).getPetrifyChance()){
                    isPetrifiedEnemy = true;
                    System.out.println("The " + enemy.getName() + " has been petrified!");
                }
            }
            if (Game.player.att.get(att).getPoisonChance() > 0){
                if (getChance() >= Game.player.att.get(att).getPoisonChance()){
                    isPoisonedEnemy = true;
                    System.out.println("The " + enemy.getName() + " has been poisoned!");
                }
            }
            if (Game.player.att.get(att).getParalyzChance() > 0){
                if (getChance() >= Game.player.att.get(att).getParalyzChance()){
                    isParylizedEnemy = true;
                    System.out.println("The " + enemy.getName() + " has been paralyzed!");
                }
            }

            return (int) damage;
        }
    }

    /**
     * Calculates the amount of damage an enemy's attack does against the player.
     * Prints out the type of attack used and how much damage it inflicted.
     * @return the damage done by the enemy attack
     */
    private int enemyAttack(){
        if (enemySelectedMove == 0){
            return 0;
        } else if (enemy.att.get(enemySelectedMove - 1).getAttackName().equalsIgnoreCase("Cookie Throw")){
            int i = (int) getChance();
            if (i == 20){
                i = (int) getChance();
                if (i == 50){
                    System.out.println("The Goblin attacked you using Cookie Throw. It did " + Integer.MAX_VALUE + " damage. Turns out those crumbs were sharp!");
                    return Integer.MAX_VALUE;
                } else {
                    System.out.println("The Goblin attacked you using Cookie Throw. It was as useless as always.");
                    return 0;
                }
            } else {
                System.out.println("The Goblin attacked you using Cookie Throw. It was as useless as always.");
                return 0;
            }

        }
        else{
            int att = enemySelectedMove - 1;
            double damage;
            damage = (((((2 * enemy.getLevel() / 5) + 2) * (enemy.getAttack() / Game.player.getDefense()) * (enemy.att.get(att).getPower())) / 50) + 2) * getCriticalHitModifier();
            System.out.println("The " + enemy.getName() + " attacked you using " + enemy.att.get(att).getAttackName() + ". It did " + (int)damage + " damage.");

            if (enemy.att.get(att).getPetrifyChance() > 0){
                if (getChance() >= enemy.att.get(att).getPetrifyChance()){
                    isPetrifiedPlayer = true;
                    System.out.println("You have been petrified!");
                }
            }
            if (enemy.att.get(att).getPoisonChance() > 0){
                if (getChance() >= enemy.att.get(att).getPoisonChance()){
                    isPoisonedPlayer = true;
                    System.out.println("You have been poisoned!");
                }
            }
            if (enemy.att.get(att).getParalyzChance() > 0){
                if (getChance() >= enemy.att.get(att).getParalyzChance()){
                    isParylizedPlayer = true;
                    System.out.println("You have been paralyzed!");
                }
            }

            return (int) damage;
        }
    }

    /**
     * Calculates the amount of damage a pet's attack does against the opponent.
     * Prints out the type of attack used and how much damage it inflicted.
     * @return the amount of damage the pet's attack does
     */
    private int petAttack(){
        double damage;
        damage = (((((2 * Game.player.getCurrentPet().getLevel() / 5) + 2) * (Game.player.getCurrentPet().getAttack() / enemy.getDefense()) * (Game.player.getCurrentPet().att.get(0).getPower())) / 50) + 2) * getCriticalHitModifier();
        System.out.println("Your " + Game.player.getCurrentPet().getNickname() + " attacked the " + enemy.getName() + " using " + Game.player.getCurrentPet().att.get(0).getAttackName() + ". It did " + (int)damage + " damage.");
        return (int) damage;
    }

    /**
     * Displays the player's and enemy's current health.
     * Used at the start of a battle and after each move
     */
    private void displayHealth(){
        System.out.println("Your health is " + Game.player.getHealthLeft() + "/" + Game.player.getHealth() + ". Your mana is " + Game.player.getManaLeft() + "/" + Game.player.getMana() + ".");
        System.out.println("The " + enemy.getName() + "'s health is " + enemy.getHealthLeft() + "/" + enemy.getHealth());
    }

    /**
     * Checks if the player still has health left.
     * @return a boolean based on whether the player is still alive
     */
    private boolean checkPlayerStatus(){
        return Game.player.getHealthLeft() > 0;
    }

    /**
     * Checks if the enemy still has health left.
     * @return a boolean based on whether the enemy is still alive
     */
    private boolean checkEnemyStatus(){
        return enemy.getHealthLeft() > 0;
    }

    /**
     * Determines whether a particular attack is a critical hit. If it is, then it deals extra damage
     * @return the multiplier for a critical hit
     */
    private int getCriticalHitModifier(){
        double c = Math.random() * (10);
        int a = (int) Math.round(c);
        if (a == 10){
            a = 2;
            System.out.println("It was a Critical Hit!");
        } else {
            a = 1;
        }
        return a;
    }

    /**
     * Generates an enemy for the player to engage based on the player's skill level
     * @return an enemy for the battle
     */
    private Enemy generateEnemy(){
        int id, amount;
        Enemy e;

        if (Game.player.getLevel() < 5){
            amount = 2;
        } else if (Game.player.getLevel() < 10){
            amount = 5;
        } else {
            amount = 8;
        }
        id = idGenerator(amount);
        e = getEnemy()[id];

        return e;
    }

    private Pet generatePet(){
        int id;
        Pet p;

        id = idGenerator(4);
        p = getPet()[id];

        return p;
    }

    /**
     * Takes in an amount and generates a random number based on that amount. Used elsewhere to
     * represent a position in an arraylist.
     * @param amount the input amount?
     * @return the random id generated
     */
    private static int idGenerator(int amount){
        double a;
        int b;

        a = Math.random() * (amount);
        b = (int) Math.round(a);

        return b;
    }

    /**
     * @return a random number between 1 and 100
     */
    private double getChance(){
        double chance = 1 + Math.random() * (100 - 1);
        chance = Math.round(chance);

        return chance;
    }

    /**
     * Each of these methods returns different types of enemies based on the player's
     * skill level.
     * @return an enemy for the battle
     */
    private static Enemy[] getEnemy(){
        return new Enemy[]{new Goblin(Game.player.getLevel()), new Skeleton(Game.player.getLevel()), new Troll(Game.player.getLevel()), new Vampire(Game.player.getLevel()), new Witch(Game.player.getLevel()), new BabyDragon(Game.player.getLevel()), new Dragon(Game.player.getLevel()), new Werewolf(Game.player.getLevel()), new RogueKnight(Game.player.getLevel())};
    }

    private static Pet[] getPet(){
        return new Pet[]{new Wolf(1), new Phoenix(1), new Baboon(1), new HobGoblin(1), new Griffin(1)};
    }
}
