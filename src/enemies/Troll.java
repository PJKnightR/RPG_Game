package enemies;

import attack.ClubBat;
import attack.CookieThrow;
import attack.SlashingClaw;
import attack.TreeBash;
import players.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public class Troll extends Enemy {

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

        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new SlashingClaw());
        atts.add(new ClubBat());
        atts.add(new TreeBash());

        attLevel = new int[]{1,4,10};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }

}
