package enemies;

import attack.ClubBat;
import attack.SlashingClaw;
import attack.genericAttack1;
import attack.genericAttack2;
import game.Player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Troll extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;

    public Troll(Player PC) {
        diff = "easy";
        name = "Troll";

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

        atts.add(new SlashingClaw());
        atts.add(new ClubBat());

        attLevel = new int[]{1,1};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }

}
