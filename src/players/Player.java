package players;

import attack.*;
import game.Actor;
import item.*;

import java.util.ArrayList;

public class Player extends Actor {
	private static final Readable IOStream = null;
	protected double experience;
	protected double mana;
	protected double baseMana;
	//private ArrayList<Item> inventory;			// Added
	//inventory should be its own class
	//keep track of mana

/*	public Player(){
		String name;
		String e_name;
		double health, defense, attack, speed, healthLeft, baseAttack, baseDefense, baseSpeed, baseHealth;
		double level;
		ArrayList<Attack> att, atts;
		int attLevel [];
	}*/

	public Player(String name){
		//inventory = new ArrayList<>();
		setName(name);
		level = 1; // when k
		setAttack();
		setDefense();
		setSpeed();
		setHealth();
		setHealthLeft(health);

		atts = new ArrayList<>();
		att = new ArrayList<>();

		atts.add(new SwordSlash());
		atts.add(new RiskyLunge());

		attLevel = new int[]{1,5};
		baseMana=50;
		setMana();

		getInitialAttacks();
	}


	//((baseAttack / 2) * lev / 100 + 5)
	//((baseAtk / 2 ) * (lev / 100) + 5)
	//base stats can be different to each class, setting stats done in constructor, called upon each level up

	/*public void addInventory(Item i) {
		this.inventory.add(i);
	}

	public Item getInv(int index) {
		return this.inventory.get(index);
	}

	public String printInventory() {
		String inv = this.name + "'s game.Inventory:\n";
		for (int i = 0; i < inventory.size(); i++) {
			inv += " " + i + ". " + inventory.get(i).getName() + "\n";
		}
		return inv;
	}*/
	public double getMana(){
		return mana;
	}

    /**
     * We are going to want initial statistic values set in the constructor for this class and later on in the ones for
     * each class
     */
	
	/**
	 * This will set the health of the player. This will change later
	 */
	public void setHealth() {
		//health = Math.round(this.getHealth()-(this.getHealth()));
		health = ((baseHealth / 2) * level / 100 + 5);
	}
	
	/**
	 * This will set the defense of the playe
	 */
	public void setDefense() {
		//defense = Math.round(this.getDefense()-(this.getDefense()));
		defense = ((baseDefense / 2) * level / 100 + 5);
	}
	
	/**
	 * This will set the attack strength of the player's move. Changes based on other stats
	 */
	public void setAttack() {
		//attack = Math.round(this.getAttack()-(this.getAttack()));
		attack = ((baseAttack / 2) * level / 100 + 5);
	}
	
	/**
	 * This will set the speed of the player but will also change in Battle
	 */
	public void setSpeed() {
		//speed = Math.round(this.getSpeed()-(this.getSpeed()));
		speed = ((baseSpeed / 2) * level / 100 + 5);
	}

	public void setMana() {
		//speed = Math.round(this.getSpeed()-(this.getSpeed()));
		mana = ((baseMana / 2) * level / 100 + 5);
	}
	/**
	 * This will set the Level of the player starting at 1
	 */
	public void setLevel() {
		level = 1;
	}
	
	
	//Keeps track of Exp to level up with
	public int Exp;
	
	/**
	 * This will set the check if the player has enough Exp to level up
	 */
	public void levelUp(){
		if (this.Exp > 100) {
			System.out.println("Level Up!!!");
			level = level+1;
		}
		else{}
	}
}
