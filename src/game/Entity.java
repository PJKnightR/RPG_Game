package game;

//import java.util.ArrayList;

import attack.Attack;
import java.util.ArrayList;
import java.util.Queue;

abstract public class Entity {
    public String name;
    protected int health;
    protected int defense;
    protected int attack;
    protected int speed;
    protected int healthLeft;
    protected int baseAttack;
    protected int baseDefense;
    protected int baseSpeed;
    protected int baseHealth;
    protected double level;
    protected ArrayList<Attack> att;
    protected int attLevel [];
    protected Queue<Attack> atts;

    /**
     * Generates the initial attacks for both players and enemies
     */
    public void getInitialAttacks(){
        int currentAtt = 0, levelCount = 0;
        while(levelCount <= this.getLevel()){
            if (atts.isEmpty()){
                break;
            } else {
                while (levelCount == attLevel[currentAtt] && !atts.isEmpty()){
                    this.att.add(atts.poll());
                    currentAtt++;
                    if (currentAtt == attLevel.length){
                        break;
                    }
                }
            }
            levelCount++;
        }
    }

    /**
     * Sets the name and is used only for Player
     * @param name name to set the player's as
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the name of either the Player
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Gets Health
     */
    public int getHealth() { return health; }

    /**
     * Gets Health Left
     */
    public int getHealthLeft() {
        return healthLeft;
    }

    /**
     * Sets the health let
     * @param healthLeft the amount of health left to set
     */
    public void setHealthLeft(int healthLeft){
        this.healthLeft = healthLeft;
    }

    /**
     * Gets Defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Gets attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets Speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets Level
     */
    public double getLevel() {
        return level;
    }

    /**
     * Sets level
     * @param level level to set to
     */
    public void setLevel(double level){
        this.level = level;
    }
}