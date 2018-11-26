package enemies;

import attack.*;
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

        atts = new ArrayList<>();
        att = new ArrayList<>();

        atts.add(new StickSlap());
        atts.add(new CookieThrow());
        atts.add(new ClubBat());

        attLevel = new int[]{1,1,2};

        getInitialAttacks();
    }

    // Should set the enemy level based on the player level
    public void setLevel(Player PC) {
        level = PC.getLevel();
    }

}