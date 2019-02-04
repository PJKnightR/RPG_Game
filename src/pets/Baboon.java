package pets;

import attack.SlashingClaw;

import java.util.ArrayList;

public class Baboon extends Pet {
    public Baboon(double l){
        name = "Baboon";
        nickname = "Baboon";

        fainted = false;
        baseAttack = 95;
        baseDefense = 80;
        baseSpeed = 65;
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
