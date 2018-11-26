package enemies;

import attack.StickSlap;
import players.Player;

import java.util.ArrayList;

public class Skeleton extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;

    public Skeleton(Player PC) {
        diff = "easy";
        name = "Skeleton";

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

        attLevel = new int[]{1};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }

}
