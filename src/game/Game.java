package game;

import java.util.Scanner;

import item.GarlicBread;
import players.*;

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

	/** Constructor Method
	 * This constructor creates a new game.
	 * @param name String
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
	public String getTitle() {
		return "\n\t" + this.name.toUpperCase() + "\n\n";
	}

	/** Creates a new character based on parameters received
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
								System.out.println("[|] Coming Soon.");
								// mainMenu = false;
								// mash = false;
								// start = true;
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
							System.out.println("Method not implemented");
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
			}

			else if (!mainMenu && !gameMenu) {	// open in-game
				// If this is a new game
				if (start) {		// open start

					// Initializing the Game.
					System.out.println(this.intro);
					if (mash) {
						System.out.println("[|] Continue your adventure by typing '1'. Open the menu by typing '2'.");
					}
					start = false;

				}	// close tart

				System.out.print(" > ");
				choice = scan.nextInt();


				if (mash) {	// open mash

					//TODO: Let them do anything besides kill stuff

					switch (choice) {
						case 1:
							Battle batbat = new Battle();
							batbat.startBattle(PC, scan);
							if (PC.getHealthLeft() <= 0) {
								System.out.print("\n\n\t\tGAME\tOVER\n\n\n");
								mainMenu = true;
								break;
							}
							break;
						case 2:
								gameMenu = true;
								break;
						default:
							System.out.println("[|] Command not recognized.");
							break;
					}

				}	// close mash
				//else if (!mash) {}

			}	// close in-game

		}	// close gameLoop


	} 	// Close runGame()
}	// Close Game{}