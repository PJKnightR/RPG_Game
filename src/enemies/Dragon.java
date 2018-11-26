package enemies;

import attack.*;
import players.Player;

import java.util.ArrayList;

public class Dragon extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;

    public Dragon(Player PC) {
        diff = "hard";
        name = "Dragon";

        baseAttack = 150;
        baseDefense = 150;
        baseSpeed = 150;
        baseHealth = 150;

        setLevel(PC);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        atts = new ArrayList<>();
        att = new ArrayList<>();

        atts.add(new SlashingClaw());
        atts.add(new FireBreath());
        atts.add(new WingSlap());

        attLevel = new int[]{1,1,1};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }

}
