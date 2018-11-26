package enemies;

import attack.RiskyLunge;
import attack.SwordSlash;
import players.Player;

import java.util.ArrayList;

public class RogueKnight extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;
    private Player player;

    public RogueKnight(Player PC) {
        diff = "hard";
        name = "Rogue Knight";

        baseAttack = 150;
        baseDefense = 150;
        baseSpeed = 150;
        baseHealth = 150;

        setLevel(PC);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        atts = new ArrayList<>();
        att = new ArrayList<>();

        atts.add(new SwordSlash());
        atts.add(new RiskyLunge());

        attLevel = new int[]{1,1};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }
}
