package enemies;

import attack.CookieThrow;
import attack.SlashingClaw;
import attack.Bash;
import attack.Bite;
import attack.Roar;
import players.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public class Werewolf extends Enemy {

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

        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new SlashingClaw());
        atts.add(new Bite());
        atts.add(new Bash());
        atts.add(new Roar());

        attLevel = new int[]{1,4,6,8};

        getInitialAttacks();
    }

    public void setLevel(Player PC) {
        level = PC.getLevel();
    }
}
