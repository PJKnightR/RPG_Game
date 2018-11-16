package game;

import java.util.Scanner;

/**	This class contains everything need to run a new game.
 *  To play, a new instance of this class must be initialized.
 */
public class Game {

	// Private Variables
	private String name;			// This is the name of the game being played
	private Scanner scan;			// This is a scanner that reads user Input
	private Player PC;				// This is avatar of the user in the game
	// private Module module;


	/**
	 * Constructor Class
	 * This constructor creates a new game.
	 * @param name Str: name
	 * @param scan Scanner: to receive user Input.
	 */
	public Game(String name, Scanner scan) { //  Module module
		// TODO: Possibly create a new class for handling and storing saves and settings
		this.name = name;
		this.scan = scan;
		// this.module = module;
	}

	/** Get Method
	 * @return The name of the game in dramatic font
	 */
	public String getTitle() {
		return "\n\t" + this.name.toUpperCase() + "\n\n";
	}

	/** Creates a new character based on userInput.
	 *  @return a new Player object
	 */
	private Player createCharacter() {
		System.out.println("Please enter character name: ");
		scan.nextLine();
		String name = scan.nextLine();
		game.Player PC = new game.Player(name);
		//Player PC = new Player();
		return PC;
	}

	/** Since we have not yet implement module, this monstrosity will have to do
	 *  It can also serve as a template for how the module classes should be constructed.
	 *
	 * @return the whole game. Yes, you heard me.
	 */

	/** Runs the game.
	 * 	Handles all events, such as userInput, game events, battles, ect.
	 */
	public void runGame() {

		// Booleans
		boolean running = true;		// This indicates whether the game is still running, e.g. menu or game.
		boolean playing = false;	// This indicates whether the game is being played. (not main menu).
		boolean start = false;		// This indicates whether game has just been started or not.

		// Main Loop of the Entire Game. Continue until quit in the main menu.
		while (running) {
			if (!playing) { // Start menu.
				// If we are not playing, bring the user to the main menu of the game.
				// Print the title and the menu options.
				System.out.print(this.getTitle());
				System.out.println(" 1. New Game" +
						         "\n 2. Load Game" +
						         "\n 3. Quit");
				// Print the prompt
				System.out.print(" > ");

				// Handle Main Menu choices
				switch (scan.nextInt()) {  // Can directly reference input here.
					case 1:
						// Starting a new game. Set playing to exit the main menu, set start since it is a new game.
						playing = true;
						start = true;
						break;

					case 2:
						// Loading a previous save. See text below.
						// We'll need to call this.PC = something.getCharacter(), and some other stuff.
						System.out.println("This feature is not yet implemented");
						// playing = true;
						start = false;
						break;
					case 3:
						// Exit the game. Set running, close the scanner, and exit.
						// NOTE: if closing scanner here, may want to ensure it is not needed in main.
						System.out.println("Thanks for playing.");
						running = false;
						scan.close();
						break;
					// None of the above, misunderstood command
					case 4:
						// This is an easter egg as a joke. Plz ignore.
						System.out.println("4 is the cosmic number.");
						break;
					default:
						System.out.println("Command not recognized.");
						break;
				}
			}  // End menu.


			else if (playing) { // Start in-game.

				// This boolean is not to be confused with the main menu. This is the in-game menu.
				// Normally in games this would likely be accessed with esc.
				boolean menu = false;


				// If this is a fresh game, than we have to initialize it.
				if (start) {
					// Create a new Player Character
					this.PC = createCharacter();

					System.out.println("You can open the menu at any time by entering 'menu'.");

					// TODO:  Here we might load a module for the rooms, ect.
					// Instead we'll use a method we'll depreciate later.

					// Initial game conditions
					System.out.println("Flavor text.txt");


					start = false;
				}

				// Otherwise, continue the game as normal.

				// Prompt and receive player input.
				System.out.print(" > ");
				String userInput = scan.next();

				/** This handles the menu, and all interactions within it.
				 * 		The user stays in the menu unless they continue or exit.
				 */
				if (userInput.equals("menu") || menu) {

					// Print the menu the first time menu is called, or when asked for again.
					if (!menu || userInput.equals("menu")) {
						System.out.println("'1' continue\n" +
								"'2' save\n" +
								"'3' help\n" +
								"'4' quit\n" +
								"'menu' to see this again.");
						menu = true;
					}
					// Closes the menu
					else if (userInput.equals("1")) {
						menu = false;
					}
					// This would save current data, using a printWriter
					else if (userInput.equals("2")) {
						//TODO: save
						System.out.println("This feature has not yet been implemented.");
					}
					// This would print out all available options
					else if (userInput.equals("3")) {
						//TODO: help options
						System.out.println("No one can help you.");
					}
					// Ends the game.
					else if (userInput.equals("4")) {
						System.out.println("Returning to main menu.");
						playing = false;
						break;
					} else {
						System.out.printf("'%s' not recognized.\n", userInput);
					}
				}

				/** This handles the basic commands in the game.
				 * 		These actions can trigger events.
				 */

				if (userInput.equals("inventory")) {
					System.out.print(PC.printInventory());
					//TODO let them do anything with it.

				} else if (userInput.equals("me")) {
					System.out.println("You looked for your qualities left and right, but could not fine them.");
					//TODO actually give them the stats, you dick

				} else if (userInput.equals("go")) {
					// TODO: Whenever we are in a room/area, get an arraylist of directions we can go.

					// Get the second part: direction
					userInput = scan.next();
					System.out.println("Actually, you can't go anywhere.");

				} else if (userInput.equals("look")) {
					// TODO: Whenever we are in a room/area, get an arraylist of things in it.
					userInput = scan.next();
					System.out.println("That isn't here.");

				} else if (userInput.equals("take")) {
					// TODO: Whenever we are in a room/area, get an arraylist of things in it.
					userInput = scan.next();
					System.out.println("That isn't here.");
				} else {
				    System.out.println("Command not recognized.");
                }

				// TODO: Detect game over conditions*/
				// 	System.out.println("\n\t GAME OVER\n\n");
			} // End in-game.

		}
	}
}
