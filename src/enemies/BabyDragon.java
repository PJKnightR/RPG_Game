package enemies;

import attack.CookieThrow;
import attack.FireBreath;
import attack.SlashingClaw;
import attack.Bash;
import attack.TailWhip;
import players.Player;

import java.util.ArrayList;
import java.util.LinkedList;


public class BabyDragon extends Enemy {

    public BabyDragon(Player PC) {
        diff = "moderate";
        name = "Baby Dragon";

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

        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new SlashingClaw());
        atts.add(new TailWhip());
        atts.add(new Bash());
        atts.add(new FireBreath());

        attLevel = new int[]{1,4,6,10};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }
}
