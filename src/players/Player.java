package players;

import attack.*;
import game.Actor;
import game.Inventory;
import item.*;

import java.util.ArrayList;

public class Player extends Actor {
	private static final Readable IOStream = null;
	protected double experience;
	Inventory inventory = new Inventory();
	public double mana, manaLeft, baseMana;

	public Player(String name){
		setName(name);
		setLevel(1);

		baseAttack = 50;
		baseDefense = 50;
		baseSpeed = 50;
		baseHealth = 50;
		baseMana = 50;

		setAttack();
		setDefense();
		setSpeed();
		setHealth();
		setHealthLeft(health);
		setMana();
		setManaLeft(mana);
		gainExp(0);

		atts = new ArrayList<>();
		att = new ArrayList<>();

		atts.add(new SwordSlash());
		atts.add(new RiskyLunge());

		attLevel = new int[]{1,5};

		getInitialAttacks();
	}

	public Inventory getInventory(){
		return inventory;
	}

    /**
     * We are going to want initial statistic values set in the constructor for this class and later on in the ones for
     * each class
     */
	
	/**
	 * This will set the health of the player. This will change later
	 */
	public void setHealth() {
		health = ((baseHealth / 2) * level / 100 + 10);
	}
	
	/**
	 * This will set the defense of the playe
	 */
	public void setDefense() {
		defense = ((baseDefense / 2) * level / 100 + 5);
	}
	
	/**
	 * This will set the attack strength of the player's move. Changes based on other stats
	 */
	public void setAttack() {
		attack = ((baseAttack / 2) * level / 100 + 5);

	}
	
	/**
	 * This will set the speed of the player but will also change in Battle
	 */
	public void setSpeed() {
		speed = ((baseSpeed / 2) * level / 100 + 5);
	}

	public void setMana(){
		mana = ((baseMana / 2) * level / 100 + 10);
	}

	public void setManaLeft(double m){
		manaLeft = m;
	}

	public double getMana(){
		return mana;
	}

	public double getManaLeft(){
		return manaLeft;
	}

	/**
	 * This will set the Level of the player starting at 1
	 */
	public void setLevel(double l) {
		level = l;
	}
	
	
	//Keeps track of Exp to level up with
	public int Exp;

	/**
	 * This will set the experience of the player starting at 0
	 * @param Experience the amount of experience to the player's current level of experience
	 */
	public void gainExp(int Experience){ Exp += Experience; }

	/**
	 * @return the player's amount of experience
	 */
	public int getExp(){ return Exp; }


	public void setExp(int i){
		this.experience = i;
	}
	/**
	 * This will check if the player has enough Exp to level up
	 */
	public boolean checkLevelUp(){
		if(this.getExp() > (100 * this.level)){
			return true;
		}
		return false;
	}

	/**
	 * Levels up the player and prints out which level they have achieved
	 */
	public void levelUp(){
		this.setLevel(this.getLevel() + 1);
		System.out.println("Level Up!!! You have reached level " + getLevel() + "!");
		this.setExp(getExp() - 100);
		this.setName(name);
		this.setAttack();
		this.setDefense();
		this.setSpeed();
		this.setHealth();
		this.setHealthLeft(health);
	}
}
