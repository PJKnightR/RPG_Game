package enemies;

import attack.*;
import attack.SpearChuck;
import players.Player;

import java.util.ArrayList;

public class Goblin extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;

    public Goblin(Player PC) {
        diff = "easy";
        name = "Goblin";

        baseAttack = 50;
        baseDefense = 50;
        baseSpeed = 50;
        baseHealth = 50;

        setLevel(PC);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        //Arraylist of possible attacks
        atts = new ArrayList<>();
        //Arraylist of what level of character needs to be to unlock an attack
        att = new ArrayList<>();

        atts.add(new CookieThrow());
        atts.add(new StickSlap());
        atts.add(new ClubBat());
        atts.add(new SpearChuck());

        attLevel = new int[]{1,2,4,10};

        getInitialAttacks();
    }

    // Should set the enemy level based on the player level
    public void setLevel(Player PC) {
        level = PC.getLevel();
    }

}
