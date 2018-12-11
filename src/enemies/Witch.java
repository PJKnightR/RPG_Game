package enemies;

import attack.MysteriousBrew;
import attack.StickSlap;
import attack.Fireball;
import attack.WickedCurse;

import java.util.ArrayList;
import java.util.LinkedList;


public class Witch extends Enemy {

    public Witch(double l) {
        diff = "moderate";
        name = "Witch";

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

        atts.add(new StickSlap());
        atts.add(new MysteriousBrew());
        atts.add(new Fireball());
        atts.add(new WickedCurse());

        attLevel = new int[]{1,4,8,10};

        getInitialAttacks();
    }

}
