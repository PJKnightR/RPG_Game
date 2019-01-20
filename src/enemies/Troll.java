package enemies;

import attack.ClubClobber;
import attack.SlashingClaw;
import attack.TreeBash;

import java.util.ArrayList;
import java.util.LinkedList;

public class Troll extends Enemy {

    public Troll(double l) {
        diff = "easy";
        name = "Troll";

        baseAttack = 75;
        baseDefense = 50;
        baseSpeed = 50;
        baseHealth = 50;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new SlashingClaw());
        atts.add(new ClubClobber());
        atts.add(new TreeBash());

        attLevel = new int[]{1,4,10};

        getInitialAttacks();
    }

}
