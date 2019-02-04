package pets;

import attack.Bite;

import java.util.ArrayList;

public class Wolf extends Pet {
    public Wolf(double l){
        name = "Wolf";
        nickname = "Wolf";

        fainted = false;
        baseAttack = 75;
        baseDefense = 70;
        baseSpeed = 85;
        baseHealth = 70;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        att = new ArrayList<>();

        att.add(new Bite());
    }
}
