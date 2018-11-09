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
     * Add the basic get() methods to this class
     */
	
	protected abstract void getName();
	
	protected abstract void getHealth();
	
	protected abstract void getHealthLeft();
	
	protected abstract void getDefence();
	
	protected abstract void getAttack();
	
	protected abstract void getDamage();
	
	protected abstract void getSpeed();
	
	protected abstract void getLevel();
}
