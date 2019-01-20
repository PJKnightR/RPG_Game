package enemies;

import attack.*;
import attack.Bash;
import attack.Bite;
import attack.ThreateningRoar;
import attack.TailWhip;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dragon extends Enemy {

    public Dragon(double l) {
        diff = "hard";
        name = "Dragon";

        baseAttack = 150;
        baseDefense = 150;
        baseSpeed = 150;
        baseHealth = 150;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new SlashingClaw());
        atts.add(new TailWhip());
        atts.add(new Bite());
        atts.add(new Bash());
        atts.add(new WingSlap());
        atts.add(new ThreateningRoar());
        atts.add(new FireBreath());

        attLevel = new int[]{1,2,3,5,6,8,10};

        getInitialAttacks();
    }

}
