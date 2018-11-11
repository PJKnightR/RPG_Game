package enemies;

import game.Actor;

abstract public class Enemy extends Actor {

	/**
	 * Sets the health of the enemy
	 */
	abstract public void setHealth();

	/**
	 * Sets the defense stat of the enemy
	 */
	abstract public void setDefense();

	/**
	 * Sets the attack stat of the enemy
	 */
	abstract public void setAttack();

	/**
	 * Should set the enemy level based on the player level
	 */
	abstract public void setLevel();

	//Speed is determined in game.Battle
	/**
	 * Set the speed of the enemy
	 */
	public void setSpeed() { speed = 1.0; }

}
