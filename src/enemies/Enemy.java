package enemies;

import game.Actor;

abstract public class Enemy extends Actor {
	/**
	 * Sets the health of the enemy
	 */
	public void health() {
		health = 100;
	}
	/**
	 * Sets the defense stat of the enemy
	 */
	public void defence() {
		defense = 50;
	}
	/**
	 * Sets the attack stat of the enemy
	 */
	public void attack() {
		attack = 50;
	}
	
	//Damage is calculated in game.Battle
	/**
	 * Sets the damage stat of a particular attack from the enemy
	 */
	public void damage() {
		//damage = (((((2 * level)/5)+2)*power*(attack/defence))/50)+2; 
		//What would Power be?
	}
	
	//Speed is determined in game.Battle
	/**
	 * Sets the speed of the enemy
	 */
	public void speed() {
		speed = 1.0;
	}
	
	// Should set the enemy level based on the player level
	// Currently sets a base level
	/**
	 * Sets the level of the enemy
	 */
	public void setLevel() {
		level = 1;
	}
}
