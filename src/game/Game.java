package game;

import java.util.Scanner;

public class Game {

	// this can be extended to create a new game. 
	
	// Variables
	private String name;
	private Scanner scan;
	private Module module;
	
	/** Constructor Class
	 *  This constructor creates a new game.
	 * @param name Str: name
	 * @param scan Scanner: to receive user Input.
	 */
	public Game (String name, Scanner scan, Module module) {
		// TODO: Possibly create a new class for handling and storing saves and settings
		this.name = name;
		this.scan = scan;
		this.module = module;
	}
	
	/**
	 * @return The name of the game in dramatic font
	 */
	public String getTitle() {
		return "\n\t" + this.name.toUpperCase() + "\n\n";
	}
	
	/** Creates a new character based on userInput.
	 * @return a new Player object
	 */
	private Player createCharacter() {
		System.out.println("Please enter character name: ");
		scan.nextLine();
		String name = scan.nextLine();
		game.Player PC = new game.Player(name);
        //Player PC = new Player();
		return PC;
	}
	
	/** Runs the game.
	 */
	public void runGame () {

		// TODO: Start of the game

		System.out.println(this.getTitle());

		// TODO: Implement saved game and/or password

		this.createCharacter();

		// TODO: Get Intro text, ect.

		// Begin the main game loop.
		boolean playing = true;
		boolean menu = false;


		// User Input is gathered first, then the results and consequences are dealt with.
		while (playing) {
			System.out.print(" > ");
			String userInput = scan.next();
			
			// User is kept in the menu until they exit it.
			if (userInput.equals("menu") || menu == true) {
				
				// Print the menu the first time menu is called, or when asked for again.
				if (menu == false || userInput.equals("menu")) {
					System.out.println("'c' continue\n'h' help\n's' save\n'q' quit\n'menu' to see this again");
					menu = true;
				}
				// Closes the menu
				else if (userInput.equals("c")) {
					menu = false;
				}
				// This would bring up the help interface. Such as available commands.
				else if (userInput.equals("h")) {
					// TODO create help
					System.out.println("No one can help you.");
				}
				// This would save current data, using a printWriter
				else if (userInput.equals("s")) {
					System.out.println("This feature has not yet been implemented.");
				}
				// Ends the game.
				else if (userInput.equals("q")) {
					System.out.println("Goodbye.");
					playing = false;
					break;
				} else {
					System.out.printf("'%s' not recognized.\n", userInput);
				}
			}


			// TODO: Detect new Input other than menu. (the sort of thing help is designed to show)

			// TODO: Handle Game Events (response to the Input)

			// TODO: Detect game over conditions
		}
		System.out.println("\n\t GAME OVER\n\n");
	}
}
