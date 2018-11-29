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
        boolean battling = true, playerLoss = false, enemyLoss = false;
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
            PC.gainExp(100);
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
    //add being able to check your stats and attacks
    public void playerMove(Player PC){
        boolean action = false;
        String move;

        displayHealth(PC);
        System.out.println("What would you like to do?\n 1. Fight\n 2. Use an item\n 3. Run");
        while (!action){
            move = scan.next();
            if (move.equalsIgnoreCase("1")){
                action = true;
                this.selectedMove = selectAttack(PC);
            } else if (move.equalsIgnoreCase("2")){
                action = true;
                this.selectedMove = 0;
                PC.getInventory().useItem(PC,this);
            } else if (move.equalsIgnoreCase("3")){
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
     * Allows the player to select which attack they would like to use
     * @param PC
     * @return an integer representing the attack type
     */
    private int selectAttack(Player PC){

        for(int i = 0; i < PC.att.size(); i++){
            System.out.print(i + 1 + ". " + PC.att.get(i).getAttackName() + " ");
        }
        System.out.println("\nEnter -1 to go back");

        //need a try catch here or convert to a string
        int attack = scan.nextInt();
        if (attack == -1){
            playerMove(PC);
        } else if (attack < -2  || attack > PC.att.size() || attack == 0) {
            System.out.println("Please enter an applicable number!");
            selectAttack(PC);
        } else if (PC.getManaLeft() < PC.att.get(attack - 1).getManaCost()){
            System.out.println("Not enough mana for this attack! Please enter an applicable number!");
            selectAttack(PC);
        }

        return attack;
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
     * Same as the playerAttack method
     * @param PC
     * @return
     */
    private int enemyAttack(Player PC){
        //damage calculation for enemy and display message for their attack
        if (enemySelectedMove == 0){
            return 0;
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
    public void displayHealth(Player PC){
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
    //purely random generation of enemies, not based on player level
    private Enemy generateEnemy(Player PC){
        int id, amount;
        double chance;
        chance = getChance();
        Enemy e;

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

        return e;
    }

    //idGenerator is a separate method like this to allow easy changes to generate enemy if more enemies are added
    //just change the amount variable as it contains the amount of enemies minus 1
    private static int idGenerator(int amount){
        double a;
        int b;

        a = Math.random() * (amount);
        b = (int) Math.round(a);

        return b;
    }

    private double getChance(){
        double chance = 1 + Math.random() * (100 - 1);
        chance = Math.round(chance);

        return chance;
    }

    private static Enemy[] getDifficultyLev1(Player PC){
        return new Enemy[]{new Goblin(PC), new Skeleton(PC), new Troll(PC)};
    }

    private static Enemy[] getDifficultyLev2(Player PC){
        return new Enemy[]{new Vampire(PC), new Witch(PC), new BabyDragon(PC)};
    }

    private static Enemy[] getDifficultyLev3(Player PC){
        return new Enemy[]{new Dragon(PC), new Werewolf(PC), new RogueKnight(PC)};
    }

}
