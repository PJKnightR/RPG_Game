package enemies;

import attack.MysteriousBrew;
import attack.StickSlap;
import attack.genericAttack1;
import attack.genericAttack2;
import game.Player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Witch extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;

    public Witch(Player PC) {
        diff = "moderate";
        name = "Witch";

        baseAttack = 100;
        baseDefense = 100;
        baseSpeed = 100;
        baseHealth = 100;

        setLevel(PC);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        atts = new ArrayList<>();
        att = new ArrayList<>();

        atts.add(new StickSlap());
        atts.add(new MysteriousBrew());

        attLevel = new int[]{1,1};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }
}
