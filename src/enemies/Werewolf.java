package enemies;

import attack.CookieThrow;
import attack.SlashingClaw;
import attack.Bash;
import attack.Bite;
import attack.Roar;
import players.Player;

import java.util.ArrayList;

public class Werewolf extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;
    private Player player;

    public Werewolf(Player PC) {
        diff = "hard";
        name = "Werewolf";

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

        atts.add(new CookieThrow());
        atts.add(new SlashingClaw());
        atts.add(new Bite());
        atts.add(new Bash());
        atts.add(new Roar());

        attLevel = new int[]{1,2,4,6,8};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }
}
