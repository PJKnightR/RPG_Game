package game;

import java.util.ArrayList;

abstract public class Actor {
	protected String name;
	protected double health, defense, attack, speed, health_left;
	protected int level;
	//protected ArrayList<item.Item> Inventory = new ArrayList<>();
	//protected String Class;

	/**
	 * This method gets Nam
	 */
	//protected abstract void getName();

	protected String getName(){
		return name;
	}

	protected void setName(String n){
		name = n;
	}
	/**
	 * This method gets Health
	 */
	protected double getHealth() {
		return health;
	}
	/**
	 * This method gets Health Left
	 */
	protected double getHealthLeft() {
		return health_left;
	}
	/**
	 * This method gets Defense
	 */
	protected double getDefense() {
		return defense;
	}
	/**
	 * This method gets attack
	 */
	protected double getAttack() {
		return attack;
	}

	/**
	 * This method gets Speed
	 */
	protected double getSpeed() {
		return speed;
	}
	/**
	 * This method gets Level
	 */
	protected int getLevel() {
		return level;
	}
}
