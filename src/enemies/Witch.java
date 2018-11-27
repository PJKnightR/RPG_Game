package enemies;

import attack.CookieThrow;
import attack.MysteriousBrew;
import attack.StickSlap;
import attack.Fireball;
import attack.WickedCurse;
import players.Player;

import java.util.ArrayList;


public class Witch extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;

    public Witch(Player PC) {
        diff = "moderate";
        name = "Witch";

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

        atts = new ArrayList<>();
        att = new ArrayList<>();

        atts.add(new CookieThrow());
        atts.add(new StickSlap());
        atts.add(new MysteriousBrew());
        atts.add(new Fireball());
        atts.add(new WickedCurse());

        attLevel = new int[]{1,1};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }
}
