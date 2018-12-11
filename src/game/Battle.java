package game;

import enemies.*;
import players.Player;
import java.util.Scanner;

public class Battle {
    private Enemy enemy;
    private Scanner scan;
    private int selectedMove, enemySelectedMove;

    /**
     * Initializes values needed to start the battle and declares what type of enemy has appeared
     * @param PC the user's character
     * @param s
     */
    public void startBattle(Player PC, Scanner s){
        this.enemy = generateEnemy(PC);
        this.scan = s;
        System.out.println("A " + enemy.getName() + " appeared. Engage the " + enemy.getName() + " " + PC.getName() + "!");
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
        double itemChance;

        while (battling){
            playerMove(PC);
            if (selectedMove == -1){
                break;
            }
            enemyMove();
            if (PC.getSpeed() >= enemy.getSpeed()){
                enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack(PC));
                battling = checkEnemyStatus();
                if (!battling){
                    System.out.println("You defeated the " + enemy.getName() + "!");
                    enemyLoss = true;
                    break;
                }
                PC.setHealthLeft(PC.getHealthLeft() - enemyAttack(PC));
                battling = checkPlayerStatus(PC);
                if (!battling){
                    System.out.println("You were defeated by the " + enemy.getName() + "!");
                    //playerLoss = true;
                    break;
                }
            } else if (PC.getSpeed() < enemy.getSpeed()){
                PC.setHealthLeft(PC.getHealthLeft() - enemyAttack(PC));
                battling = checkPlayerStatus(PC);
                if (!battling){
                    System.out.println("You were defeated by the " + enemy.getName() + "!");
                    //playerLoss = true;
                    break;
                }
                enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack(PC));
                battling = checkEnemyStatus();
                if (!battling){
                    System.out.println("You defeated the " + enemy.getName() + "!");
                    enemyLoss = true;
                    break;
                }
            }
        }

        if (enemyLoss){
            itemChance = getChance();
            if (itemChance > 50){
                PC.getInventory().addRandomItem();
            }
            if (enemy.getDifficulty().equalsIgnoreCase("easy")){
                PC.gainExp(50);
            } else if (enemy.getDifficulty().equalsIgnoreCase("moderate")){
                PC.gainExp(100);
            } else if (enemy.getDifficulty().equalsIgnoreCase("hard")){
                PC.gainExp(250);
            } else {
                PC.gainExp(100);
            }
            // check if the player is eligible to level up
            if(PC.checkLevelUp()){
                PC.levelUp();
            }
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
                if (run > 50){
                    System.out.println("You escaped from the " + enemy.getName());
                    this.selectedMove = -1;
                    //end the battle here
                } else {
                    System.out.println("The " + enemy.getName() + " blocks your escape path!");
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
        + "/" + PC.getMana() + "\nExperience: " + PC.getExp() + "/" + 100 * PC.getLevel() + "\nEnter -1 to go back.\n");

        i = scan.next();
        while (!i.equals("-1")){
            System.out.println("Please enter a valid number!");
            i = scan.next();
        }
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

            damage = (2 * PC.getLevel() + 10) / 250 * (PC.getAttack() / enemy.getDefense()) * (PC.att.get(att).getPower() + 2) * getCriticalHitModifier();
            System.out.println("You attack using " + PC.att.get(att).getAttackName() + ". It did " + (int)damage + " damage.");

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
            damage = (2 * enemy.getLevel() + 10) / 250 * (enemy.getAttack() / PC.getDefense()) * (enemy.att.get(att).getPower() + 2) * getCriticalHitModifier();
            System.out.println("The " + enemy.getName() + " attacked you using " + enemy.att.get(att).getAttackName() + ". It did " + (int)damage + " damage.");

            return (int) damage;
        }
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
        if (PC.getHealthLeft() <= 0){
            return false;
        }

        return true;
    }

    /**
     * Checks if the enemy still has health left.
     * @return a boolean based on whether the enemy is still alive
     */
    private boolean checkEnemyStatus(){
        if (enemy.getHealthLeft() <= 0){
            return false;
        }

        return true;
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
        double chance;
        chance = getChance();
        Enemy e;

        if (PC.getLevel() < 5){
            amount = 2;
            id = idGenerator(amount);
            e = getDifficultyLev1(PC)[id];
        } else if (PC.getLevel() < 10){
            if (chance <= 80){
                amount = 2;
                id = idGenerator(amount);
                e = getDifficultyLev1(PC)[id];
            } else {
                amount = 2;
                id = idGenerator(amount);
                e = getDifficultyLev2(PC)[id];
            }
            assert e != null;
        } else {
            if (chance <= 60){
                amount = 2;
                id = idGenerator(amount);
                e = getDifficultyLev1(PC)[id];
            } else if (chance <= 89){
                amount = 2;
                id = idGenerator(amount);
                e = getDifficultyLev2(PC)[id];
            } else {
                amount = 2;
                id = idGenerator(amount);
                e = getDifficultyLev3(PC)[id];
            }
            assert e != null;
        }

        return e;
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
    private static Enemy[] getDifficultyLev1(Player PC){
        return new Enemy[]{new Goblin(PC.getLevel()), new Skeleton(PC.getLevel()), new Troll(PC.getLevel())};
    }

    private static Enemy[] getDifficultyLev2(Player PC){
        return new Enemy[]{new Vampire(PC.getLevel()), new Witch(PC.getLevel()), new BabyDragon(PC.getLevel())};
    }

    private static Enemy[] getDifficultyLev3(Player PC){
        return new Enemy[]{new Dragon(PC.getLevel()), new Werewolf(PC.getLevel()), new RogueKnight(PC.getLevel())};
    }
}
