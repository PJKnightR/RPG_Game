package pets;

import attack.Fireball;

import java.util.ArrayList;

public class Phoenix extends Pet {
    public Phoenix(double l){
        name = "Phoenix";
        nickname = "Phoenix";

        fainted = false;
        baseAttack = 85;
        baseDefense = 65;
        baseSpeed = 85;
        baseHealth = 70;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        att = new ArrayList<>();

        att.add(new Fireball());
    }
}
