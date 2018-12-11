package enemies;

import attack.SlashingClaw;
import attack.WingSlap;
import attack.SwordSlash;
import attack.Fireball;
import attack.Bite;

import java.util.ArrayList;
import java.util.LinkedList;


public class Vampire extends Enemy {

    public Vampire(double l) {
        diff = "moderate";
        name = "Vampire";

        baseAttack = 100;
        baseDefense = 100;
        baseSpeed = 100;
        baseHealth = 100;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new SlashingClaw());
        atts.add(new Bite());
        atts.add(new WingSlap());
        atts.add(new Fireball());
        atts.add(new SwordSlash());

        attLevel = new int[]{1,3,5,8,10};

        getInitialAttacks();
    }

}
