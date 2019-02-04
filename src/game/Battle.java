package game;

import enemies.*;
import pets.Baboon;
import pets.Pet;
import pets.Phoenix;
import pets.Wolf;
import players.Player;
import java.util.Scanner;

public class Battle {
    private Enemy enemy;
    private Pet pet;
    private Scanner scan;
    private int selectedMove, enemySelectedMove;
    private boolean petBattle, canRun, isPoisonedPlayer, isPoisonedEnemy, isPetrifiedPlayer, isPetrifiedEnemy, isParylizedPlayer, isParylizedEnemy;

    public Battle(){
        isParylizedEnemy = false;
        isParylizedPlayer = false;
        isPetrifiedEnemy = false;
        isPetrifiedPlayer = false;
        isPoisonedEnemy = false;
        isPoisonedPlayer = false;

    }

    /**
     * Initializes values needed to start the battle and declares what type of enemy has appeared
     * @param PC the user's character
     * @param s
     */
    public void startBattle(Player PC, Scanner s, Boolean r){
        this.enemy = generateEnemy(PC);
        this.scan = s;
        canRun = r;
        System.out.println("A " + enemy.getName() + " appeared. Engage the " + enemy.getName() + " " + PC.getName() + "!");
        if (getChance() < 10 && !PC.hasPet){
            petBattle = true;
            this.pet = generatePet();
            System.out.println("The " + enemy.getName() + " was attacking a " + pet.getName() + ". Save them!");
        }
        doBattle(PC);
    }

    /**
     * Runs the process of performing a battle by calling the move, attack and health methods for both the player and enemy.
     * Compares the speed of the enemy and player. Whoever is faster gets to attack first.
     * Prints out a message declaring a winner at the end of each battle.
     * @param PC
     */
    private void doBattle(Player PC){
        boolean battling = true, enemyLoss = false;

        while (battling){
            if (isPoisonedPlayer){
                if (getChance() > 50){
                    isPoisonedPlayer = false;
                    System.out.println("You are no longer poisoned");
                } else {
                    PC.setHealthLeft(PC.getHealthLeft() - (int)(PC.getHealth() * .5));
                    System.out.println("You took " + (int)(PC.getHealth() * .5) + " damage from poison.");
                    battling = checkPlayerStatus(PC);
                    if (!battling){
                        System.out.println("You were defeated by the " + enemy.getName() + "!");
                        break;
                    }
                }
            }

            if (isPoisonedEnemy){
                if (getChance() > 50){
                    isPoisonedEnemy = false;
                    System.out.println("The " + enemy.getName() + " is no longer poisoned");
                } else {
                    enemy.setHealthLeft(enemy.getHealthLeft() - (int)(enemy.getHealth() * .5));
                    System.out.println("The " + enemy.getName() + " took " + (int)(enemy.getHealth() * .5) + " damage from poison.");
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
                    if (getChance() > 50){
                        isPetrifiedPlayer = false;
                        System.out.println("You are no longer petrified");
                    }
                }

                if(!isPetrifiedPlayer){
                    playerMove(PC);
                    if (selectedMove == -1){
                        break;
                    }
                    enemyMove();
                }

                if(isParylizedPlayer){
                    if (getChance() > 50){
                        isParylizedPlayer = false;
                        System.out.println("The " + enemy.getName() + " is no longer petrified");
                    }
                }

                if (isPetrifiedEnemy){
                    if (getChance() > 50){
                        isPetrifiedEnemy = false;
                        System.out.println("The " + enemy.getName() + " is no longer petrified");
                    }
                }

                if(isParylizedEnemy){
                    if (getChance() > 50){
                        isParylizedEnemy = false;
                        System.out.println("The " + enemy.getName() + " is no longer paralized");
                    }
                }

                if (PC.getSpeed() >= enemy.getSpeed()){
                    if(!isPetrifiedPlayer){
                        if(isParylizedPlayer){
                            if(getChance() > 50){
                                System.out.println("You are paralyzed but managed to move");
                                enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack(PC));
                                if(PC.hasPet){
                                    if(!PC.getCurrentPet().isFainted()){
                                        enemy.setHealthLeft(enemy.getHealthLeft() - petAttack(PC));
                                    }
                                }
                                battling = checkEnemyStatus();
                                if (!battling){
                                    System.out.println("You defeated the " + enemy.getName() + "!");
                                    enemyLoss = true;
                                    break;
                                }
                            } else {
                                System.out.println("You are paralyzed and cannot move!");
                            }
                        } else {
                            enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack(PC));
                            if(PC.hasPet){
                                if(!PC.getCurrentPet().isFainted()){
                                    enemy.setHealthLeft(enemy.getHealthLeft() - petAttack(PC));
                                }
                            }
                            battling = checkEnemyStatus();
                            if (!battling){
                                System.out.println("You defeated the " + enemy.getName() + "!");
                                enemyLoss = true;
                                break;
                            }
                        }
                    }
                    if (isPetrifiedPlayer){
                        System.out.println("You are petrified and cannot move");
                    }
                    if(!isPetrifiedEnemy){
                        if(isParylizedEnemy){
                            if(getChance() > 50){
                                System.out.println("The enemy is paralyzed but managed to attack!");
                                PC.setHealthLeft(PC.getHealthLeft() - enemyAttack(PC));
                                battling = checkPlayerStatus(PC);
                                if (!battling){
                                    System.out.println("You were defeated by the " + enemy.getName() + "!");
                                    break;
                                }
                            } else {
                                System.out.println("The enemy is paralyzed and cannot move");
                            }
                        } else {
                            PC.setHealthLeft(PC.getHealthLeft() - enemyAttack(PC));
                            battling = checkPlayerStatus(PC);
                            if (!battling){
                                System.out.println("You were defeated by the " + enemy.getName() + "!");
                                break;
                            }
                        }
                    }
                    if (isPetrifiedEnemy){
                        System.out.println("The " + enemy.getName() + " is petrified and cannot move");
                    }
                } else if (PC.getSpeed() < enemy.getSpeed()){
                    if(!isPetrifiedEnemy){
                        if(isParylizedEnemy){
                            if(getChance() > 50){
                                System.out.println("The enemy is paralyzed but managed to attack!");
                                PC.setHealthLeft(PC.getHealthLeft() - enemyAttack(PC));
                                battling = checkPlayerStatus(PC);
                                if (!battling){
                                    System.out.println("You were defeated by the " + enemy.getName() + "!");
                                    break;
                                }
                            } else {
                                System.out.println("The enemy is paralyzed and cannot move");
                            }
                        } else {
                            PC.setHealthLeft(PC.getHealthLeft() - enemyAttack(PC));
                            battling = checkPlayerStatus(PC);
                            if (!battling){
                                System.out.println("You were defeated by the " + enemy.getName() + "!");
                                break;
                            }
                        }
                    }
                    if (isPetrifiedEnemy){
                        System.out.println("The " + enemy.getName() + " is petrified and cannot move");
                    }

                    if(!isPetrifiedPlayer){
                        if(isParylizedPlayer){
                            if(getChance() > 50){
                                System.out.println("You are paralyzed but managed to move");
                                enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack(PC));
                                if(PC.hasPet){
                                    if(!PC.getCurrentPet().isFainted()){
                                        enemy.setHealthLeft(enemy.getHealthLeft() - petAttack(PC));
                                    }
                                }
                                battling = checkEnemyStatus();
                                if (!battling){
                                    System.out.println("You defeated the " + enemy.getName() + "!");
                                    enemyLoss = true;
                                    break;
                                }
                            } else {
                                System.out.println("You are paralyzed and cannot move!");
                            }
                        } else {
                            enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack(PC));
                            if(PC.hasPet){
                                if(!PC.getCurrentPet().isFainted()){
                                    enemy.setHealthLeft(enemy.getHealthLeft() - petAttack(PC));
                                }
                            }
                            battling = checkEnemyStatus();
                            if (!battling){
                                System.out.println("You defeated the " + enemy.getName() + "!");
                                enemyLoss = true;
                                break;
                            }
                        }
                    }
                    if (isPetrifiedPlayer){
                        System.out.println("You are petrified and cannot move");
                    }
                }
            }
        }

        if (enemyLoss){
            battleEnd(PC);
        }
    }

    public void battleEnd(Player PC){
        double itemChance;

        if(petBattle){
            PC.setPet(pet);
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
                PC.getCurrentPet().setNickname(scan.nextLine());
            }
        }

        itemChance = getChance();
        if (itemChance > 50){
            PC.getInventory().addRandomItem();
        }
        if (enemy.getDifficulty().equalsIgnoreCase("easy")){
            PC.gainExp(50 + (int)getChance());
            PC.gainGold(100 + (int)getChance());
        } else if (enemy.getDifficulty().equalsIgnoreCase("moderate")){
            PC.gainExp(100 + (int)getChance());
            PC.gainGold(150 + (int)getChance());
        } else if (enemy.getDifficulty().equalsIgnoreCase("hard")){
            PC.gainExp(250 + (int)getChance());
            PC.gainGold(200 + (int)getChance());
        } else {
            PC.gainExp(100);
            PC.gainGold(100);
        }

        if(PC.hasPet){
            PC.getCurrentPet().gainExp(50);
            if(PC.getCurrentPet().checkLevelUp()){
                PC.getCurrentPet().levelUp();
            }
        }

        if(PC.checkLevelUp()){
            PC.levelUp();
        }
    }

    /**
     * Allows the user to choose their own move whenever an enemy appears. They may choose to fight, use an item, or run away
     * @param PC
     */
    public void playerMove(Player PC){
        boolean action = false;
        String move;

        displayHealth(PC);
        System.out.println("What would you like to do? (Enter the corresponding number):\n 1. Fight\n 2. Use an item\n 3. View Your Stats\n 4. Run");
        while (!action){
            move = scan.next();
            if (move.equals("1")){
                action = true;
                selectAttack(PC);
            } else if (move.equals("2")){
                action = true;
                this.selectedMove = 0;
                PC.getInventory().useItem(PC,this);
            } else if(move.equals("3")){
               displayPlayerStats(PC);
                System.out.println("What would you like to do?\n 1. Fight\n 2. Use an item\n 3. View Your Stats\n 4. Run");
            } else if (move.equals("4")){
                action = true;
                this.selectedMove = 0;
                double run = getChance();
                if (run > 50 && canRun){
                    System.out.println("You escaped from the " + enemy.getName());
                    this.selectedMove = -1;
                } else {
                    if (!canRun){
                        System.out.println("You cannot run from this battle.");
                    } else {
                        System.out.println("The " + enemy.getName() + " blocks your escape path!");
                    }
                }
            } else {
                System.out.println("Please enter a valid move");
            }
        }
    }

    /**
     * Prints out all of the player's current stats including their name, health, skill level, attack strength
     * defensive strength, speed, mana, and amount of experience.
     * @param PC the player whose stats the method needs to print out
     */
    private void displayPlayerStats(Player PC){
        String i;

        System.out.print("Name: " + PC.getName() + " Level: " + PC.getLevel() + "\nHealth: " + PC.getHealthLeft() + "/" + PC.getHealth() + "\nAttack: "
                + PC.getAttack() + " (+" + PC.getEquipped().getDamage() + " from " + PC.getEquipped().getItemName() + ")\nDefense: " + PC.getDefense() + " (+" + PC.getWorn().getProtection() + " from " + PC.getWorn().getItemName() + ")\nSpeed: " + PC.getSpeed() + "\nMana: " + PC.getManaLeft()
                + "/" + PC.getMana() + "\nExperience: " + PC.getExp() + "/" + 100 * PC.getLevel() + " Gold: " + PC.getGold());
        if(PC.hasPet){
            System.out.print("\n\nName: " + PC.getCurrentPet().getNickname() + "\nType: " + PC.getCurrentPet().getName() +
                    "Level: " + PC.getCurrentPet().getLevel() + "\nHealth: " + PC.getCurrentPet().getHealthLeft() + "/" + PC.getCurrentPet().getHealth()
                    + "\nAttack: " + PC.getCurrentPet().getAttack() + "\nDefense: " + PC.getCurrentPet().getDefense() +
                    "\nSpeed: " + PC.getCurrentPet().getSpeed() + "\nExperience: " + PC.getCurrentPet().getExp() + "/" + 100 * PC.getCurrentPet().getLevel());
        }

        System.out.println("\nEnter -1 to go back");
        i = scan.next();
        while (!i.equals("-1")){
            System.out.println("Please enter a valid number!");
            i = scan.next();
        }
        displayHealth(PC);
    }

    /**
     * Allows the player to select which attack they would like to use
     * @param PC
     * @return an integer representing the attack type
     */
    private void selectAttack(Player PC){
        int attack, r;
        String a;
        boolean done = false;

        for(int i = 0; i < PC.att.size(); i++){
            System.out.print(i + 1 + ". " + PC.att.get(i).getAttackName() + " (Mana Cost: " + PC.att.get(i).getManaCost() + ") ");
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
                    playerMove(PC);
                } else if (attack < -2  || attack > PC.att.size() || attack == 0) {
                    System.out.println("Please enter an applicable number!");
                } else if (PC.getManaLeft() < PC.att.get(attack - 1).getManaCost()){
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
        //random selection of the enemies move
        double enemyMove = 1 + Math.random() * (enemy.att.size() - 1);
        enemySelectedMove = (int)enemyMove;
    }

    private void playerDamageStep(){

    }

    private void enemyDamageStep(){

    }

    /**
     * Calculates the amount of damage a single player move does against the opponent.
     * Prints out the type of attack used and how much damage it inflicted.
     * @param PC
     * @return
     */
    private int playerAttack(Player PC){
        if (selectedMove == 0){
            return 0;
        } else {
            int att = selectedMove - 1;
            double damage;

            PC.setManaLeft(PC.getManaLeft() - PC.att.get(att).getManaCost());

            damage = (((((2 * PC.getLevel() / 5) + 2) * (PC.getAttack() / enemy.getDefense()) * (PC.att.get(att).getPower())) / 50) + 2) * getCriticalHitModifier();
            System.out.println("You attack using " + PC.att.get(att).getAttackName() + ". It did " + (int)damage + " damage.");

            if (PC.att.get(att).getPetrifyChance() > 0){
                if (getChance() >= PC.att.get(att).getPetrifyChance()){
                    isPetrifiedEnemy = true;
                    System.out.println("The " + enemy.getName() + " has been petrified!");
                }
            }
            if (PC.att.get(att).getPoisonChance() > 0){
                if (getChance() >= PC.att.get(att).getPoisonChance()){
                    isPoisonedEnemy = true;
                    System.out.println("The " + enemy.getName() + " has been poisoned!");
                }
            }
            if (PC.att.get(att).getParalyzChance() > 0){
                if (getChance() >= PC.att.get(att).getParalyzChance()){
                    isParylizedEnemy = true;
                    System.out.println("The " + enemy.getName() + " has been paralyzed!");
                }
            }

            return (int) damage;
        }
    }

    /**
     * Same as the playerAttack method but for the enemy
     * @param PC
     * @return
     */
    private int enemyAttack(Player PC){
        if (enemySelectedMove == 0){
            return 0;
        } else if (enemy.att.get(enemySelectedMove - 1).getAttackName().equalsIgnoreCase("Cookie Throw")){
            int i = (int) getChance();
            if (i == 20){
                i = (int) getChance();
                if (i == 50){
                    System.out.println("The Goblin attacked you using Cookie Throw. It did " + Integer.MAX_VALUE + " damage. The Goblin got you fam.");
                    return Integer.MAX_VALUE;
                } else {
                    System.out.println("The Goblin attacked you using Cookie Throw. It was as usless as always.");
                    return 0;
                }
            } else {
                System.out.println("The Goblin attacked you using Cookie Throw. It was as usless as always.");
                return 0;
            }

        }
        else{
            int att = enemySelectedMove - 1;
            double damage;
            damage = (((((2 * enemy.getLevel() / 5) + 2) * (enemy.getAttack() / PC.getDefense()) * (enemy.att.get(att).getPower())) / 50) + 2) * getCriticalHitModifier();
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

    private int petAttack(Player PC){
        double damage;
        damage = (((((2 * PC.getCurrentPet().getLevel() / 5) + 2) * (PC.getCurrentPet().getAttack() / enemy.getDefense()) * (PC.getCurrentPet().att.get(0).getPower())) / 50) + 2) * getCriticalHitModifier();
        System.out.println("Your " + PC.getCurrentPet().getNickname() + " attacked the " + enemy.getName() + " using " + PC.getCurrentPet().att.get(0).getAttackName() + ". It did " + (int)damage + " damage.");
        return (int) damage;
    }

    /**
     * Displays the player's and enemy's current health.
     * Used at the start of a battle and after each move
     * @param PC
     */
    private void displayHealth(Player PC){
        System.out.println("Your health is " + PC.getHealthLeft() + "/" + PC.getHealth() + ". Your mana is " + PC.getManaLeft() + "/" + PC.getMana() + ".");
        System.out.println("The " + enemy.getName() + "'s health is " + enemy.getHealthLeft() + "/" + enemy.getHealth());
    }

    /**
     * Checks if the player still has health left.
     * @param PC
     * @return a boolean based on whether the player is still alive
     */
    private boolean checkPlayerStatus(Player PC){
        return PC.getHealthLeft() > 0;
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
     * @return
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
     * @param PC
     * @return
     */
    private Enemy generateEnemy(Player PC){
        int id, amount;
        Enemy e;

        if (PC.getLevel() < 5){
            amount = 2;
        } else if (PC.getLevel() < 10){
            amount = 5;
        } else {
            amount = 8;
        }
        id = idGenerator(amount);
        e = getEnemy(PC)[id];

        return e;
    }

    private Pet generatePet(){
        int id;
        Pet p;

        id = idGenerator(2);
        p = getPet()[id];

        return p;
    }

    /**
     * Takes in an amount and generates a random number based on that amount. Used elsewhere to
     * represent a position in an arraylist.
     * @param amount
     * @return
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
     * @param PC
     * @return
     */

    private static Enemy[] getEnemy(Player PC){
        return new Enemy[]{new Goblin(PC.getLevel()), new Skeleton(PC.getLevel()), new Troll(PC.getLevel()), new Vampire(PC.getLevel()), new Witch(PC.getLevel()), new BabyDragon(PC.getLevel()), new Dragon(PC.getLevel()), new Werewolf(PC.getLevel()), new RogueKnight(PC.getLevel())};
    }

    private static Pet[] getPet(){
        return new Pet[]{new Wolf(1), new Phoenix(1), new Baboon(1)};
    }
}
