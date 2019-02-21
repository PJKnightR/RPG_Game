package enemies;

import attack.WickedCurse;

import java.util.ArrayList;
import java.util.LinkedList;

public class KingOfShadows extends Enemy{

    public KingOfShadows(double l) {
        diff = "boss";
        name = "King of Shadows";

        baseAttack = 300;
        baseDefense = 300;
        baseSpeed = 300;
        baseHealth = 300;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new WickedCurse());

        attLevel = new int[]{1};

        getInitialAttacks();
    }
}
