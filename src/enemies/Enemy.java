package enemies;

import game.Actor;
import players.Player;

abstract public class Enemy extends Actor {
	// Indicates the enemy's difficulty
	public String diff;

	/**
	 * Sets the health of the enemy
	 */
	public void setHealth(){
		health = (int)(((baseHealth + 31 + level * 4) / 2) * level / 100 + 10);
	}

	/**
	 * Sets the defense stat of the enemy
	 */
	public void setDefense(){
		defense = (int)(((baseDefense + 31 + level * 4) / 2) * level / 100 + 5);
	}

	/**
	 * Sets the attack stat of the enemy
	 */
	public void setAttack(){
		attack = (int)(2 *(((baseAttack + 31 + level * 4) / 2) * level / 100 + 5));
	}

	public void setHealthLeft(int h){
		healthLeft = h;
	}

	/**
	 * Set the speed of the enemy
	 */
	public void setSpeed() {
		speed = (int)(((baseSpeed + 31 + level * 4) / 2) * level / 100 + 5);;
	}

	/**
	 * @return the difficulty level of the enemy
	 */
	public String getDifficulty(){
		return diff;
	}
}
