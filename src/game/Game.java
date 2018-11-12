package game;

import java.util.Scanner;

public class Game {

	// this can be extended to create a new game. 

	// Variables
	private String name;
	private Scanner scan;
	// private Module module;
	private Player PC;

	/**
	 * Constructor Class
	 * This constructor creates a new game.
	 *
	 * @param name Str: name
	 * @param scan Scanner: to receive user Input.
	 */
	public Game(String name, Scanner scan) { //  Module module
		// TODO: Possibly create a new class for handling and storing saves and settings
		this.name = name;
		this.scan = scan;
		// this.module = module;
	}

	/**
	 * @return The name of the game in dramatic font
	 */
	public String getTitle() {
		return "\n\t" + this.name.toUpperCase() + "\n\n";
	}

	/**
	 * Creates a new character based on userInput.
	 *
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

	/**
	 * Runs the game.
	 */
	public void runGame() {

		boolean running = true;
		boolean playing = false;
		boolean start = false;

		while (running) {
			// Default value, enters the main menu.
			if (!playing) {
				// Print Main Menu
				System.out.print(this.getTitle());
				System.out.println("1. New Game" +
						"\n2. Load Game" +
						"\n3. Quit");

				// Handle Main Menu
				switch (scan.nextInt()) {
					case 1:
						playing = true;
						start = true;
						break;
					case 2:
						System.out.println("This feature is not yet implemented");
						// playing = true;
						start = false;
						break;
					case 3:
						System.out.println("Thanks for playing.");
						running = false;
						scan.close();
						break;
					default:
						System.out.println("Command not recognized.");
						break;
				}

				// When the game begins.
			} else if (playing) {
				boolean menu = false;

				// If this is a new game
				if (start) {
					// Create a new Player Character
					this.PC = createCharacter();

					System.out.println("There is a goblin in your closet. What do you do?");
					System.out.println("'g' Give it a cookie" +
										"\n'f' run away screaming" +
										"\n'k' kill it");

					start = false;
				}

				System.out.print(" > ");
				String userInput = scan.next();

				// User is kept in the menu until they exit it.
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
					if (userInput.equals("1")) {
						menu = false;
					}
					// This would save current data, using a printWriter
					if (userInput.equals("2")) {
						//TODO: save
						System.out.println("This feature has not yet been implemented.");
					}
					// This would print out all available options
					if (userInput.equals("3")) {
						//TODO: help options
						System.out.println("No one can help you.");
					}
					// Ends the game.
					if (userInput.equals("4")) {
						System.out.println("Returning to main menu.");
						playing = false;
						break;
					} else {
						System.out.printf("'%s' not recognized.\n", userInput);
					}
				}

				if (userInput.equals("g")) {
					System.out.println("You don't have a cookie. The goblin is disappointed.");
					Battle bat = new Battle();
					bat.startBattle(PC, scan);

				} else if (userInput.equals("f")) {
					System.out.println("The crafty goblin has a rope around your leg.");
					Battle bat = new Battle();
					bat.startBattle(PC, scan);

				} else if (userInput.equals("k")) {
					System.out.println("The goblin predicted this");
					Battle bat = new Battle();
					bat.startBattle(PC, scan);
				} else {
					System.out.println("Command not recognized.");
				}


				// TODO: Detect new Input other than menu. (the sort of thing help is designed to show)

				// TODO: Handle Game Events (response to the Input)

				// TODO: Detect game over conditions*/
				// 	System.out.println("\n\t GAME OVER\n\n");
			}

		}
	}
}
