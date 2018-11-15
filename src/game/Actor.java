package game;

//import java.util.ArrayList;

import attack.Attack;
import java.util.ArrayList;

abstract public class Actor {
    public String name;
    public String e_name;
    public double health, defense, attack, speed, health_left;
    public double level;
    public ArrayList<Attack> att, atts;
    public int attLevel [];
    //protected ArrayList<item.Item> Inventory = new ArrayList<>();
    //protected String Class;


    public void getInitialAttacks(){
        int currentAtt = 0, levelCount = 1;
        while(levelCount <= this.level){
            if (levelCount == attLevel[currentAtt]){
                att.add(atts.get(currentAtt));
                currentAtt++;
            }

            levelCount++;
        }
    }

    /**
     * This method sets the name and is used only for Player
     * @param n
     */
    public void setName(String n){
        this.name = n;
    }

    /**
     * This method gets the name of either the Player
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * This method should return the name of the enemy
     * @param foe --> should be the name of a subclass of 'Enemy'
     * @return e_name
     */
    public String getEnemyName(String foe){
        //this.e_name = foe.name;
        return e_name;
    }


    /**
     * This method gets Health
     */
    public double getHealth() { return health; }

    /**
     * This method gets Health Left
     */
    public double getHealthLeft() {
        return health_left;
    }

    public void setHealthLeft(double i){
        health_left = i;
    }

    /**
     * This method gets Defense
     */
    public double getDefense() {
        return defense;
    }

    /**
     * This method gets attack
     */
    public double getAttack() {
        return attack;
    }

    /**
     * This method gets Speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * This method gets Level
     */
    public double getLevel() {
        return level;
    }
}