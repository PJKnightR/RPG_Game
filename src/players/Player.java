package players;

import game.Actor;
import game.Inventory;
import item.Armor;
import item.Weapon;

abstract public class Player extends Actor {
	public Inventory inventory = new Inventory();
	public Weapon equipped;
	public Armor worn;
	protected int mana, manaLeft, baseMana;
	protected int Exp;
	String weaponType;

	public Armor getWorn(){
		return worn;
	}

	public Weapon getEquipped(){
		return equipped;
	}

	public String getWeaponType(){
		return weaponType;
	}

	public Inventory getInventory(){
		return inventory;
	}

	/**
	 * This will set the health of the player. This will change later
	 */
	public void setHealth() {
		health = (int)(((baseHealth + 31 + level * 4) / 2) * level / 100 + 25);
	}
	
	/**
	 * This will set the defense of the player
	 */
	public void setDefense() {
		defense =(int) (((baseDefense + 31 + level * 4) / 2) * level / 100 + 5) + worn.getProtection();
	}
	
	/**
	 * This will set the attack strength of the player's move. Changes based on other stats
	 */
	public void setAttack() {
		attack = (int)(((baseAttack + 31 + level * 4) / 2) * level / 100 + 5) + equipped.getDamage();
	}
	
	/**
	 * This will set the speed of the player but will also change in Battle
	 */
	public void setSpeed() {
		speed = (int) (((baseSpeed + 31 + level * 4) / 2) * level / 100 + 5);
	}

	public void setMana(){
		mana = (int)(((baseMana + 31 + level * 4) / 2) * level / 100 + 20);
	}

	public void setManaLeft(int m){
		manaLeft = m;
	}

	public int getMana(){
		return mana;
	}

	public int getManaLeft(){
		return manaLeft;
	}

	public void setEquipped(Weapon w){
		this.equipped = w;
	}

	public void setWorn(Armor a){
		this.worn = a;
	}

	/**
	 * This will set the experience of the player starting at 0
	 * @param Experience the amount of experience to the player's current level of experience
	 */
	public void gainExp(int Experience){
		Exp += Experience;
		System.out.println("You gained " + Experience + " experience points!");
	}

	/**
	 * @return the player's amount of experience
	 */
	public int getExp(){ return Exp; }


	public void setExp(int i){
		this.Exp = i;
	}
	/**
	 * This will check if the player has enough Exp to level up
	 */
	public boolean checkLevelUp(){
		if(this.getExp() >= (100 * this.level)){
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
		double e = 100 * this.level - getExp();
		setExp((int) e);
		this.setName(name);
		this.setAttack();
		this.setDefense();
		this.setSpeed();
		this.setHealth();
		this.setHealthLeft(health);
		this.setMana();
		this.setManaLeft(mana);
		checkNewAttack();
	}

	/**
	 * Checks to see if your player is eligible to learn new attack(s). If it is, then the new attack(s)
	 * are added to the arraylist containing the player's current available attacks.
	 */
	public void checkNewAttack(){
		if (this.att.size() < 4){
			if (this.getLevel() == this.attLevel[att.size()]){
				this.att.add(atts.poll());
				System.out.print("Congratulations, you learned the attack " + att.get(att.size() - 1).getAttackName() + "! ");
			}
		}
	}
}
