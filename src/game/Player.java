package game;


public class Player extends Actor {
	private static final Readable IOStream = null;

	public Player(String name){
		setName(name);
	}

    /**
     * We are going to want initial statistic values set in the constructor for this class and later on in the ones for
     * each class
     */

	/**
	 * This will ask the Player to type in their name.
	 */
	//public void getName() {
		//Scanner in = new Scanner(IOStream);
		//name = in.next();
		//in.close();
	//}
	
	/**
	 * This will set the health of the player. This will change later
	 */
	public void setHealth() {
		health = 100;
	}
	
	/**
	 * This will set the defense of the player
	 */
	public void setDefense() {
		defense = 100;
	}
	
	/**
	 * This will set the attack strength of the player's move. Changes based on other stats
	 */
	public void setAttack() {
		attack = 70;
	}
	
	/**
	 * Damage will be changed in Battle
	 */
	public void damage() {
		//damage = (((((2 * level)/5)+2)*power*(attack/defence))/50)+2; 
		//What would Power be?
	}
	
	/**
	 * This will set the speed of the player but will also change in Battle
	 */
	public void setSpeed() {
		speed = 1.0;
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
		else{};
	}

	//@Override
	/**
	 * This will get the health of the player
	 */
	/*protected void getHealth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * This will get the health of the player left after an attack is dealt to the player
	 */
	/*protected void getHealthLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * This will get the health of the player
	 */
	/*protected void getDefence() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * This will get the health of the player based on other variables
	 */
	/*protected void getAttack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * This will get the health of the player based on other variables
	 */
	/*protected void getDamage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * This will get the speed of the player
	 */
	/*protected void getSpeed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * This will get the level of the player
	 */
	/*protected void getLevel() {

	}*/
	
	
}
