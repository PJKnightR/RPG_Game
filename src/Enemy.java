
abstract public class Enemy extends Actor {
	
	public void health() {
		health = 100;
	}
	
	public void defence() {
		defense = 50;
	}
	
	public void attack() {
		attack = 50;
	}
	
	//Damage is calculated in Battle
	public void damage() {
		//damage = (((((2 * level)/5)+2)*power*(attack/defence))/50)+2; 
		//What would Power be?
	}
	
	//Speed is determined in Battle
	public void speed() {
		speed = 1.0;
	}
	
	// Should set the enemy level based on the player level
	// Currently sets a base level
	public void setLevel() {
		level = 1;
	}
}
