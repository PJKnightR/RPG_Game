package game;

import java.util.ArrayList;

abstract public class Actor {
	protected String name;
	protected int health;
	protected int defense;
	protected int attack;
	protected int damage;
	protected double speed;
	protected int level;
	//protected ArrayList<item.Item> Inventory = new ArrayList<>();
	//protected String Class;

	/**
	 * This method gets Name
	 */
	protected abstract void getName();
	/**
	 * This method gets Health
	 */
	protected  void getHealth() {
		
	}
	/**
	 * This method gets Health Left
	 */
	protected void getHealthLeft() {
		
	}
	/**
	 * This method gets Defence
	 */
	protected  void getDefence() {
		
	}
	/**
	 * This method gets attack
	 */
	protected  void getAttack() {
		
	}
	/**
	 * This method gets Damage
	 */
	protected  void getDamage() {
		
	}
	/**
	 * This method gets Speed
	 */
	protected  void getSpeed() {
		
	}
	/**
	 * This method gets Level
	 */
	protected  void getLevel() {
		
	}
}
