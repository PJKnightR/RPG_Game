package game;

//import java.util.ArrayList;

import attack.Attack;
import java.util.ArrayList;
import java.util.Queue;

abstract public class Actor {
    public String name;
    protected double health, defense, attack, speed, healthLeft, baseAttack, baseDefense, baseSpeed, baseHealth;
    protected double level;
    protected ArrayList<Attack> att;
    protected int attLevel [];
    protected Queue<Attack> atts;

    public void getInitialAttacks(){
        int currentAtt = 0, levelCount = 0;
        while(levelCount <= this.getLevel()){
            if (atts.size() == 0){
                break;
            } else {
                while (levelCount == attLevel[currentAtt] && atts.size() > 0){
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
     * This method gets Health
     */
    public double getHealth() { return health; }

    /**
     * This method gets Health Left
     */
    public double getHealthLeft() {
        return healthLeft;
    }

    public void setHealthLeft(double i){
        healthLeft = i;
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