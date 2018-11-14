package game;

import enemies.*;
import java.util.Scanner;

public class Battle {
    public Enemy enemy;
    public Scanner scan;
    public int selectedMove;
    public int enemySelectedMove;

    public void startBattle(Player PC, Scanner s){
        this.enemy = generateEnemy();
        this.scan = s;
        System.out.println("A " + enemy.getName() + " appeared. Engage the " + enemy.getName() + " " + PC.getName() + "!");
        doBattle(PC);
    }

    public Enemy generateEnemy(){
        return new Base_Enemy();
    }

    public void doBattle(Player PC){
        boolean battling = true, playerLoss = false, enemyLoss = false;

        while (battling){
            playerMove(PC);
            enemyMove();
            if (PC.getSpeed() >= enemy.getSpeed()){
                enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack(PC));
                battling = checkEnemyStatus();
                if (!battling){
                    System.out.println("You defeated the " + enemy.getName() + "!");
                    enemyLoss = true;
                }
                enemyAttack();
                battling = checkPlayerStatus(PC);
                if (!battling){
                    playerLoss = true;
                }
            } else if (PC.getSpeed() < enemy.getSpeed()){
                enemyAttack();
                battling = checkPlayerStatus(PC);
                if (!battling){
                    playerLoss = true;
                }
                enemy.setHealthLeft(enemy.getHealthLeft() - playerAttack(PC));
                battling = checkEnemyStatus();
                if (!battling){
                    System.out.println("You defeated the " + enemy.getName() + "!");
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

    public void playerMove(Player PC){
        boolean action = false;
        String move;

        System.out.println("What would you like to do?\n 1. Fight\n 2. Use an item\n 3. Run");
        while (!action){
            move = scan.nextLine();
            if (move.equalsIgnoreCase("1")){
                action = true;
                this.selectedMove = selectAttack(PC);
            } else if (move.equalsIgnoreCase("2")){
                action = true;
                this.selectedMove = 0;
                System.out.println("This feature is coming soon!");
                //need to implement items
            } else if (move.equalsIgnoreCase("3")){
                action = true;
                this.selectedMove = 0;
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

    public int selectAttack(Player PC){

        for(int i = 0; i < PC.att.size(); i++){
            System.out.print(i + 1 + ". " + PC.att.get(i).getAttackName());
        }
        System.out.println("\nEnter -1 to go back");

        int attack = scan.nextInt();
        if (attack == -1){
            playerMove(PC);
        } else if (attack < -2  || attack > PC.att.size() || attack == 0) {
            System.out.println("Please enter an applicable number!");
            selectAttack(PC);
        }

        return attack;
    }

    public void enemyMove(){
        //random selection of the enemies move
    }

    public int playerAttack(Player PC){
        if (selectedMove == 0){
            return 0;
        } else {
            int att = selectedMove - 1;
            double damage;

            damage = (2 * PC.getLevel() + 10) / 250 * (PC.getAttack() / enemy.getDefense()) * (PC.att.get(att).getPower() + 2);
            System.out.println("You attack using " + PC.att.get(att).getAttackName() + ". It did " + (int)damage + ".");

            return (int) damage;
        }
    }

    public int enemyAttack(Player PC){
        //damage calculation for enemy and display message for their attack
        if (enemySelectedMove == 0){
            return 0;
        }
        else{
            int att = enemySelectedMove - 1;
            double damage;
            damage = (2 * enemy.getLevel() + 10) / 250 * (enemy.getAttack() / PC.getDefense()) * enemy.att.get(att).getPower() + 2);
            System.out.println("The " + enemy.getName() + " attacked you using " + enemy.att.get(att).getAttackName() + ". It did " + (int)damage + ".");

            return (int) damage;
        }
    }

    public void displayHealth(){

    }

    public boolean checkPlayerStatus(Player PC){
        if (PC.getHealthLeft() > 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEnemyStatus(){
        if (enemy.getHealthLeft() > 0){
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
