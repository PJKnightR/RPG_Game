package enemies;

import attack.*;
import attack.Bash;
import attack.Bite;
import attack.Roar;
import attack.TailWhip;
import players.Player;

import java.util.ArrayList;

public class Dragon extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;

    public Dragon(Player PC) {
        diff = "hard";
        name = "Dragon";

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

        atts.add(new SlashingClaw());
        atts.add(new TailWhip());
        atts.add(new Bite());
        atts.add(new Bash());
        atts.add(new WingSlap());
        atts.add(new Roar());
        atts.add(new FireBreath());

        attLevel = new int[]{1,1,1};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }

}
