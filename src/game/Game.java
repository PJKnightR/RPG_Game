package game;

import java.util.Scanner;
import players.*;

/** This class contains everything needed to run a new game.
 * To play, a new instance of this class must be intialized.
 */
public class Game {		// Open Game{}
	// private variables
	private String name;	// This is the name of the game being played.
	private Scanner scan;	// This is a Scanner which handles user input
	private Player PC;		// This is the avatar of the user in the game
	private String intro;

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
		this.PC = new Player(name);
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
			else if (!mainMenu) {	// open in-game
				// If this is a new game
				if (start) {		// open tart
					this.createCharacter();

					// Initializing the Game.
					System.out.println(this.intro);
					System.out.println("[|] You can open the menu at any time by entering 'menu'.");
					if (mash) {
						System.out.println("[|] Search for enemies by typing '1'.");
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
							} else {
								System.out.println("[!] You won!\n[|] type '1' to seek another target.");
							}
							break;
						default:

							break;
					}

				}	// close mash
				//else if (!mash) {}

			}	// close in-game

		}	// close gameLoop


	} 	// Close runGame()
}	// Close Game{}