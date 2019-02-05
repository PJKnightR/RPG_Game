package pets;

import attack.ClubClobber;

import java.util.ArrayList;

public class HobGoblin extends Pet{
    public HobGoblin(double l){
        name = "Hob-goblin";
        nickname = "Hob-goblin";

        fainted = false;
        baseAttack = 70;
        baseDefense = 85;
        baseSpeed = 60;
        baseHealth = 85;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        att = new ArrayList<>();

        att.add(new ClubClobber());
    }
}
