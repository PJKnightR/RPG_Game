package enemies;

import game.Actor;
import players.Player;

abstract public class Enemy extends Actor {

	/**
	 * Sets the health of the enemy
	 */
	public void setHealth(){
		health = ((baseHealth / 2) * level / 100 + 5);
	}

	/**
	 * Sets the defense stat of the enemy
	 */
	public void setDefense(){
		defense = ((baseDefense / 2) * level / 100 + 5);
	}

	/**
	 * Sets the attack stat of the enemy
	 */
	public void setAttack(){
		attack = ((baseAttack / 2) * level / 100 + 5);
	}

	/**
	 * Should set the enemy level based on the player level
	 */
	abstract public void setLevel(Player PC);

	public void setHealthLeft(double h){
		healthLeft = h;
	}

	//Speed is determined in game.Battle
	/**
	 * Set the speed of the enemy
	 */
	public void setSpeed() {
		speed = ((baseSpeed / 2) * level / 100 + 5);
	}



	/*Make different types of attacks for enemies; create and organize the different atts for Enemies;
	implement different att for Enemies;
	 */
	//Change how enemy level is set

}
