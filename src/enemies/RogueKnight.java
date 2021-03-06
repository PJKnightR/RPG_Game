package enemies;

import attack.CookieThrow;
import attack.RiskyLunge;
import attack.SwordSlash;
import attack.StickSlap;
import attack.MightyBlow;
import attack.Bash;
import players.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public class RogueKnight extends Enemy {

    public RogueKnight(double l) {
        diff = "hard";
        name = "Rogue Knight";

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

        atts.add(new StickSlap());
        atts.add(new SwordSlash());
        atts.add(new Bash());
        atts.add(new RiskyLunge());
        atts.add(new MightyBlow());

        attLevel = new int[]{1,4,5,7,9};

        getInitialAttacks();
    }

}
