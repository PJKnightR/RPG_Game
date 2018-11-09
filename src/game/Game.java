package game;

import java.util.Scanner;

public class Game {

	// this can be extended to create a new game. 
	
	// Variables
	private String name;
	private Scanner scan;
	
	/**  Creates a new, basic game
	 * 
	 */
	public Game () {
		Scanner scan = new Scanner(System.in);
		this.name = "nameless";
	}
	
	/** Creates a specific, basic game.
	 * 
	 * @param name of this game
	 * @param scan to get user input
	 */
	public Game (String name, Scanner scan) {
		this.name = name;
		this.scan = scan;
	}
	
	/**
	 * @return The name of the game in dramatic font
	 */
	public String getTitle() {
		return "\n\t" + this.name.toUpperCase() + "\n\n";
	}
	
	/** Gets the parameters for a new character
	 * 
	 * @return a new character
	 */
	private Player createCharacter() {
		
		System.out.println("Please enter character name: ");
		scan.nextLine();
		String name = scan.nextLine();
		
		//game.Player PC = new game.Player(name, 0);
        Player PC = new Player();
		return PC;
	}
	
	/**
	 *  Runs the game. 
	 */
	public void runGame () {
		boolean playing = true;
		boolean menu = false;
		
		while (playing) {
			System.out.print(" > ");
			String userInput = scan.next();
			
			// as long as the user has no quit the menu, remain in it. 
			if (userInput.equals("menu") || menu == true) {
				// Since we want the menu to persist until 
				
				// We only want it to print the menu once. 
				// We can use this catch for both scenarios. 
				if (menu == false || userInput.equals("menu")) {
					System.out.print("'c' continue\n'h' help\n's' save\n'q' quit");
					menu = true;
				}
				else if (userInput.equals("c")) {
					menu = false;
				}
				else if (userInput.equals("h")) {
					System.out.println("No one can help you.");
				}
				else if (userInput.equals("s")) {
					System.out.println("This feature has not yet been implimented.");
				}
				else if (userInput.equals("q")) {
					System.out.println("Goodbye.");
					playing = false;
					break;
				} else {
					System.out.printf("'%s' not recognized.\n", userInput);
				}
			} 
			// TODO: Detect new Input other than menu.
			// TODO: Detect game over conditions
		}
		System.out.println("\n\t GAME OVER\n\n");
	}
}
