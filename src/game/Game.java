package game;

import java.util.Scanner;

import item.*;
import players.*;
import shop.LocalShop;
import shop.Shop;

/** This class contains everything needed to run a new game.
 * To play, a new instance of this class must be initialized.
 */
public class Game {		// Open Game{}
	// private variables
	private String name;	// This is the name of the game being played.
	private Scanner scan;	// This is a Scanner which handles user input
	private Player PC;		// This is the avatar of the user in the game
	private String intro;	// This is the introduction text.
	private int PC_class; // This is temporary to make restarts possible
	private boolean dungeon;
	private final int battleChance = 75, equipmentChance = 10;

	/** Constructor Method
	 * This constructor creates a new game.
	 * @param name String: the name of the game.
	 * @param scan Scanner: to receive user input
	 */
	public Game(String name, Scanner scan, String intro) {
	this.name = name;
	this.scan = scan;
	this.intro = intro;
	}

	/** Get Method
	 * @return the name of the game in dramatic font
	 */
	private String getTitle() {
		return "\n\t" + this.name.toUpperCase() + "\n\n";
	}

	/** Creates a new character based on parameters received through user input.
	 */
	private void createCharacter() {
		System.out.print("[|] Please enter your character's name: ");
		scan.nextLine();
		String name = scan.nextLine();


		System.out.print("[|] Please Choose your class:\n 1. Archer\n 2. Knight\n 3. Wizard\n > ");
		int choice = scan.nextInt();

		switch (choice) {
			case 1:
				this.PC = new Archer(name);
				this.PC_class = 0;
				break;
			case 2:
				this.PC = new Knight (name);
				this.PC_class = 1;
				break;
			case 3:
				this.PC = new Wizard(name);
				this.PC_class = 2;
				break;
			default:
				System.out.println("[|] Command not recognized.");
				break;
		}
	}

	/** Runs the game.
	 * Handles all events, user input, ect.
	 */
	public void runGame() {	// Open runGame()
		// Boolean variables
		boolean running = true;	// Whether or not the game is being played.
		boolean mainMenu = true;	// Whether or not the game is in the main menu
		boolean mash = true;	// Whether playing mash or adventure
		boolean gameMenu = false;	// Whether or not the in-game menu is open
		boolean start = false;	// Whether this is a new Game or not

		while (running) {	// open gameLoop
			int choice = 0;	// user's input

			if (mainMenu) {	// open mainMenu
				// Print Title and menu options, and prompt
				System.out.print(    this.getTitle() +
						" 1. New Game" +
						"\n 2. Load Game" +
						"\n 3. About" +
						"\n 5. Quit" +
						"\n > "
				);
				choice = scan.nextInt();	// Get User Input

				// Handle Main Menu choices
				switch (choice) {
					case 1:
						// Here is a lovely little secondary menu.
						System.out.print(	"\n 1. Monster Mash" +
								"\n 2. Adventure" +
								"\n > ");
						choice = scan.nextInt();	// Get User Input

						// Handle Secondary Menu choices
						switch(choice) {
							case 1:
								mainMenu = false;
								mash = true;
								start = true;

								this.createCharacter(); // doing this here makes restarts more smooth
								break;
							case 2:
								mainMenu = false;
								mash = false;
								start = true;

								this.createCharacter(); // doing this here makes restarts more smooth
								break;
							default:
								System.out.println("[|] Command not recognized.");
								break;
						}
						break;
					case 2:
						System.out.println("[|] Coming Soon.");
						break;
					case 3:
						System.out.println("//TODO: Give Credit.");
						break;
					case 4:
						// This is an easter egg joke. Plz ignore.
						System.out.println("[4] Four is the cosmic number.");
						break;
					case 5:
						// Exit the game.
						System.out.print("[|] Thanks for playing.");
						running = false;
						scan.close();
						break;
					default:
						System.out.println("[!] Command not recognized.");
						break;
				}
			}	// close mainMenu

			else if (gameMenu) {
				while (gameMenu) {
					// Print Menu
					System.out.print("Menu:\n" +
							" 1. Continue\n" +
							" 2. Inventory\n" +
							" 3. Stats\n" +
							" 4. Restart\n" +
							" 5. Quit\n" +
							" > "
					);

					choice = scan.nextInt();
					switch (choice) {
						case 1:
							gameMenu = false;
							break;
						case 2:
							PC.getInventory().useItemsOutside(PC);
							break;
						case 3:
							String i;

							System.out.print("Name: " + PC.getName() + " Level: " + PC.getLevel() + "\nHealth: " + PC.getHealthLeft() + "/" + PC.getHealth() + "\nAttack: "
									+ PC.getAttack() + " (+" + PC.getEquipped().getDamage() + " from " + PC.getEquipped().getItemName() + ")\nDefense: " + PC.getDefense() + " (+" + PC.getWorn().getProtection() + " from " + PC.getWorn().getItemName() + ")\nSpeed: " + PC.getSpeed() + "\nMana: " + PC.getManaLeft()
									+ "/" + PC.getMana() + "\nExperience: " + PC.getExp() + "/" + 100 * PC.getLevel() + "Gold:" + PC.getGold() + "\nEnter -1 to go back.\n");

							i = scan.next();
							while (!i.equals("-1")){
								System.out.println("Please enter a valid number!");
								i = scan.next();
							}
							break;
						case 4:
							gameMenu = false;
							start = true;

							switch(PC_class) {
								case 0:
									PC = new Archer(PC.getName());
									break;
								case 1:
									PC = new Knight (PC.getName());
									break;
								case 2:
									PC = new Wizard(PC.getName());
									break;
								default:
									PC = new Knight(PC.getName());
									break;
									// IF YOU'RE WONDERING WHY ITS ALWAYS KNIGHT,
									// IT'S BECAUSE YOU DIDN'T UPDATE FOR NEW CLASSES HERE AND IN CREATE_CHARACTER
							}

							// Free Garlic Bread for restart
                            double chanceGarlic = 1 + (Math.random()*(2 - 1));
							chanceGarlic = Math.round(chanceGarlic);
							//System.out.println("Chance of Garlic was " + chanceGarlic);
							if (chanceGarlic == 1 ) {
								System.out.println(" Free Garlic Bread, compliments of the dungeon");
								PC.inventory.addNewItem(new GarlicBread(1));
							}

							gameMenu = false;
							break;
						case 5:
							mainMenu = true;
							gameMenu = false;
							break;
						default:
							System.out.println("[|] Command not recognized.");
							break;
					}
				}
				System.out.println("Continue your adventure by typing '1'. Open the menu by typing '2'.");
			}

			else if (!mainMenu && !gameMenu) {	// open in-game
				// If this is a new game
				if (start) {		// open start

					// Initializing the Game.
					System.out.println(this.intro);
					//if (mash) {
						System.out.println("[|] Start your adventure by typing '1'. Open the menu by typing '2'.");
					//}
					start = false;

				}	// close tart


				// Main Branch opener
				System.out.print(" > ");
				choice = scan.nextInt();

				if (mash) { // open mash

					switch (choice) {

						case 1:
							Battle batbat = new Battle();
							batbat.startBattle(PC, scan);
							if (PC.getHealthLeft() <= 0) {
								System.out.print("\n\n\t\tGAME\tOVER\n\n\n");
								mainMenu = true;
								break;
							} else {
								findNewEquipment();
								System.out.println("Enter '1' to continue your adventure! Enter '2' to go to the menu.");
							}
							break;

						case 2:
							gameMenu = true;
							break;
						default:
							System.out.println("[|] Command not recognized.");
							break;
					}

				} // close mash

				else if (!mash) {	// open adventure

					switch (choice) {
						case 1:
							int c = getChance();
							if (c <= battleChance){
								Battle batbat = new Battle();
								batbat.startBattle(PC, scan);
								if (PC.getHealthLeft() <= 0) {
									System.out.print("\n\n\t\tGAME\tOVER\n\n\n");
									mainMenu = true;
									break;
								} else {
									findNewEquipment();
									System.out.println("Enter '1' to continue your adventure! Enter '2' to go to the menu.");
								}
							} else {
								dungeon = false;
								getEvent(PC);
								if (dungeon){
									String dun;
									double l = 3 + Math.random() * (5 - 3);
									int j = (int) Math.round(l);
									for(int i = 0; i < j; i++){
										Battle batbat = new Battle();
										batbat.startBattle(PC, scan);
										if (PC.getHealthLeft() <= 0) {
											System.out.print("You have failed the dungeon and lost.");
											break;
										}
										if (i + 1 != j){
											findNewEquipment();
											System.out.println("Enter '1' to continue the dungeon.");
											dun = scan.next();
											while (!dun.equals("1")){
												System.out.println("Enter '1' to continue the dungeon.");
												dun = scan.next();
											}
										} else {
											System.out.println("You have completed the dungeon and found some Ultimate " +
													"Cheesy Garlic Bread at the end of it.");
											PC.getInventory().addNewItem(new UltimateCheesyGarlicBread(1));
										}

									}
								}
								if (PC.getHealthLeft() <= 0) {
									System.out.print("\n\n\t\tGAME\tOVER\n\n\n");
									mainMenu = true;
									break;
								} else {
									System.out.println("Enter '1' to continue your adventure! Enter '2' to go to the menu.");
								}
							}
							break;
						case 2:
								gameMenu = true;
								break;
						default:
							System.out.println("[|] Command not recognized.");
							break;
					}

				}	// close adventure

			}	// close in-game

		}	// close gameLoop


	} 	// Close runGame()

	/** Used to get random chance of events
	 * @return an integer between 1 and 100.
	 */
	private int getChance(){
		double chance;
		int i;

		chance = Math.random() * 100;
		i = (int) Math.round(chance);

		return i;
	}

	/** Gets a random event during play
	 * @param PC the player character
	 */
	private void getEvent(Player PC){
		double l = 1 + Math.random() * (9 - 1);
		int j = (int) Math.round(l);

		switch (j){
			case 1:
				System.out.println("You found a small village! In the village you find a local shop.");
				Shop shop = new LocalShop();
				shop.shop(PC);
				break;
			case 2:
				System.out.println("You found a forest!");
				PC.getInventory().addRandomItem();
				break;
			case 3:
				System.out.println("You found a cave!");
				PC.getInventory().addRandomItem();
				break;
			case 4:
				System.out.println("You found a river!");
				PC.getInventory().addRandomItem();
				break;
			case 5:
				System.out.println("You found a roadside inn!\n Your health and mana were fully restored after a nights rest.");
				PC.setHealthLeft(PC.getHealth());
				PC.setManaLeft(PC.getMana());
				break;
			case 6:
				System.out.println("You found a lake!");
				PC.getInventory().addRandomItem();
				break;
			case 7:
				if (PC.getHealthLeft() > 0){
					System.out.println("You fell into a pit of spikes someone set. Luckily, it was not fatal, but you took 5 damage");
				} else {
			   		System.out.println("You fell into a pit of spikes someone set. Unfortunatly, it was fatal.");
				}
				PC.setHealthLeft(PC.getHealthLeft() - 5);
				break;
			case 8:
				String s;
				boolean complete = false;
				dungeon = false;
				System.out.println("You have stumbled upon a dungeon, do you wish to proceed in search of treasure? Enter '1' for yes or '2' for no." +
						"\n(You will need to fight at least 3 - 5 consecutive battles to beat the dungeon).");
				s = scan.next();
				while (!complete){
					dungeon = false;
					if (s.equals("1")){
						System.out.println("You have chosen to enter the dungeon");
						dungeon = true;
						complete = true;
					} else if (s.equals("2")){
						System.out.println("You have chosen not to enter the dungeon");
						complete = true;
					} else {
						System.out.println("Invalid input entered. Please enter '1' for yes or '2' for no.");
						s = scan.next();
					}
				}
				break;
			case 9:
				System.out.println("It began to rain garlic bread, and you managed to pick some up before it hit the ground!");
				double k = 1 + Math.random() * (2);
				int f = (int) Math.round(k);
				PC.getInventory().addNewItem(new GarlicBread(f));
				break;
		}
	}

	private void findNewEquipment(){
		int type = 0;
		if (getChance() < equipmentChance){
			type = 1;
		} else if (getChance() < equipmentChance){
			type = 2;
		}
		if (type != 0) {
			int amount, id;
			if (PC.getLevel() < 10) {
				amount = 1;
			} else if (PC.getLevel() < 25) {
				amount = 3;
			} else if (PC.getLevel() < 50) {
				amount = 5;
			} else {
				amount = 6;
			}
			id = idGenerator(amount);
			if (type == 1){
				Item item = weaponList()[id];
				System.out.println("You found a " + item.getItemName());
				PC.getInventory().addNewItem(item);
			} else {
				Item item = armorList()[id];
				System.out.println("You found a " + item.getItemName());
				PC.getInventory().addNewItem(item);
			}

		}
	}

	private Weapon[] weaponList(){
		return new Weapon[]{new Travelers(1,PC.getWeaponType()), new Standard(1,PC.getWeaponType()), new Soldiers(1,PC.getWeaponType()), new Warriors(1,PC.getWeaponType()), new Guardians(1,PC.getWeaponType()), new Heros(1,PC.getWeaponType()), new Legends(1,PC.getWeaponType())};
	}

	private Armor[] armorList(){
		return new Armor[]{new LeatherChaps(1), new Chainmail(1), new IronPlatemail(1), new SilverPlatemail(1), new TitaniumPlatemail(1), new SteelPlatemail(1), new DragonScalePlatemail(1)};
	}

	private static int idGenerator(int amount){
		double a;
		int b;

		a = Math.random() * (amount);
		b = (int) Math.round(a);

		return b;
	}
}	// Close Game{}
