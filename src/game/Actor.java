package game;

import java.util.ArrayList;

abstract public class Actor {
	protected String name;
	protected double health, defense, attack, speed, health_left;
	protected int level;
	//protected ArrayList<item.Item> Inventory = new ArrayList<>();
	//protected String Class;

    /**
     * Add the basic get() methods to this class
     */
	
	protected abstract void getName();
	
	protected double getHealth(){
		return health;
	}
	
	protected double getHealthLeft(){
		return health_left;
	}
	
	protected double getDefense(){
		return defense;
	}
	
	protected double getAttack(){
		return attack;
	}
	
	protected double getSpeed(){
		return speed;
	}
	
	protected int getLevel(){
		return level;
	}
}
