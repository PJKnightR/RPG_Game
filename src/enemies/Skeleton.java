package enemies;

import attack.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Skeleton extends Enemy {

    public Skeleton(double l) {
        diff = "easy";
        name = "Skeleton";

        baseAttack = 70;
        baseDefense = 55;
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

        atts.add(new StickSlap());
        atts.add(new SlingShot());
        atts.add(new BoneThrow());
        atts.add(new SpookyDance());

        attLevel = new int[]{1,4,8,12};

        getInitialAttacks();
    }

}
