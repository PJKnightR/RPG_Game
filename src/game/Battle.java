package game;

import enemies.Enemy;
import java.util.Scanner;

public class Battle {
    public Enemy enemy;
    public Scanner scan = new Scanner(System.in);

    public void startBattle(Player PC){
        //enemy = generateEnemy();
        System.out.println("A " + enemy.getName() + " appeared. Engage the " + enemy.getName() + " " + PC.getName());
        doBattle(PC);
    }

    //public Enemy generateEnemy(){
        //waiting for Paul to finish enemy child class
    //}

    public void doBattle(Player PC){
        boolean battling = true, playerLoss = false, enemyLoss = false;

        while (battling){
            playerMove();
            enemyMove();
            if (PC.getSpeed() < enemy.getSpeed()){
                playerAttack();
                battling = checkEnemyLoss();
                if (!battling){
                    enemyLoss = true;
                }
                enemyAttack();
                battling = checkPlayerLoss(PC);
                if (!battling){
                    playerLoss = true;
                }
            } else if (PC.getSpeed() >= enemy.getSpeed()){
                enemyAttack();
                battling = checkPlayerLoss(PC);
                if (!battling){
                    playerLoss = true;
                }
                playerAttack();
                battling = checkEnemyLoss();
                if (!battling){
                    enemyLoss = true;
                }
            }

        }

        if (playerLoss){
            //game over
        } else if (enemyLoss){
            //stuff when you win a fight
        }
    }

    public void playerMove(){
        boolean action = false;
        String move = "";

        System.out.println("What would you like to do?\n 1. Fight\n 2. Use an item\n 3. Run");
        while (!action){
            move = scan.nextLine();
            if (move.equalsIgnoreCase("1")){

                action = true;
            } else if (move.equalsIgnoreCase("2")){

                action = true;
            } else if (move.equalsIgnoreCase("3")){
                action = true;
                double run = getChance();
                if (run > 50){
                    System.out.print("You escaped from the " + enemy.getName());
                    //end the battle here
                } else {
                    System.out.print("The " + enemy.getName() + " blocks your escape path!");
                }
            } else {
                System.out.println("Please enter a valid move");
            }
        }
    }

    public void enemyMove(){

    }

    public void playerAttack(){

    }

    public void enemyAttack(){

    }

    public void displayHealth(){

    }

    public boolean checkPlayerLoss(Player PC){
        if (PC.getHealth() > 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEnemyLoss(){
        if (enemy.getHealth() > 0){
            return true;
        } else {
            return false;
        }
    }

    public void getCriticalHitModifier(){

    }

    private double getChance(){
        double chance = 1 + Math.random() * (100 - 1);
        chance = Math.round(chance);

        return chance;
    }

    /**startBattle()
     * -Initializes values needed to start the battle. Will most likely be where the players current health is imported
     *
     * generateEnemy()
     * -may do this in a separate method somewhere, easier to implement things like bosses, may put in game (unless
     * fighting more than one enemy at a time is a thing). Will be using the players level in some fashion.
     *
     * playerMove()
     * -Where player chooses their move, this value will be sent to methods following this. This is also where items and
     * whatnot can be used during battle, and actions other than attacks will have their own specific numbers sent to
     * playerAttack() where that methods will determine if the player attack or did not. This will allow a smoother
     * implementation of being able to use items immediately
     *
     * enemyMove()
     * -Where enemy chooses their move, this value will be sent to methods following this. By implementing this as a
     * separate method this time, it could allow enemies to get an attack in before a player uses an item or runs if
     * attacks like that are implemented ie Pursuit from Pokemon. Maybe a weak "Quick Jab" move idk
     *
     * compareSpeeds()
     * -Figures out who will attack first by comparing the enemy and player speeds
     *
     * playerAttack()
     * -does the action of attacking the enemy and damage calculation. Will determine if the player even attacked or not.
     * This method will do nothing if the player did not choose an attack
     *
     * enemyAttack()
     * -does the action of attacking the player and damage calculation. Attacks for enemies and players should be stored
     * in arrayList. This will make them less buggy and prevent people from using attacks they don't have/don't exist.
     *
     * displayHealth()
     * -displays the current health of the player and the enemy
     *
     * checkPlayerLoss()
     * -makes sure the player is still alive. If not then game over.
     *
     * checkEnemyLoss()
     * -makes sure the enemy is still alive. Deals out experience, possible items, ect. Will partially handle level ups.
     *
     * getCriticalHitModifier()
     * -determines if the attack is critical or not
     */
}
