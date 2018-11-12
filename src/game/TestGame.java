package game;

import java.util.Scanner;

public class TestGame {

    public void battleTest(){
        Player PC = new Player("Tester");
        int i = 0;
        Scanner scan = new Scanner(System.in);

        while (i == 0){
            Battle bat = new Battle();
            bat.startBattle(PC, scan);
            i = scan.nextInt();
        }
    }
}
