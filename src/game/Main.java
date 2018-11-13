package game;

import java.util.Scanner;

public class Main {
	/**
	 * This class will be responsible for starting the game
	 */

    public static void main(String[] args) {
		TestGame test = new TestGame();
		test.battleTest();

		Scanner scan = new Scanner(System.in);
		Game newGame = new Game("test", scan);
		newGame.runGame();
    }
}
