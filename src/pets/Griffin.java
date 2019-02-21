package pets;

import attack.SlashingClaw;

import java.util.ArrayList;

public class Griffin extends Pet{
    public Griffin(double l){
        name = "Griffin";
        nickname = "Griffin";

        fainted = false;
        baseAttack = 90;
        baseDefense = 60;
        baseSpeed = 90;
        baseHealth = 75;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        att = new ArrayList<>();

        att.add(new SlashingClaw());
    }
}
