package enemies;

import attack.SlashingClaw;
import attack.WingSlap;
import players.Player;

import java.util.ArrayList;


public class Vampire extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;

    public Vampire(Player PC) {
        diff = "moderate";
        name = "Vampire";

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

        atts.add(new SlashingClaw());
        atts.add(new WingSlap());

        attLevel = new int[]{1,1};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }
}
