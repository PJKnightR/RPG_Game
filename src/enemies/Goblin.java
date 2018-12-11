package enemies;

import attack.*;
import attack.SpearChuck;
import players.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public class Goblin extends Enemy {

    public Goblin(double l) {
        diff = "easy";
        name = "Goblin";

        baseAttack = 70;
        baseDefense = 55;
        baseSpeed = 50;
        baseHealth = 50;

        setLevel(l);
        setAttack();
        setDefense();
        setHealth();
        setHealthLeft(health);
        setSpeed();

        //Arraylist of possible attacks
        atts = new LinkedList<>();
        //Arraylist of what level of character needs to be to unlock an attack
        att = new ArrayList<>();

        atts.add(new CookieThrow());
        atts.add(new StickSlap());
        atts.add(new ClubBat());
        atts.add(new SpearChuck());

        attLevel = new int[]{1,2,4,10};

        getInitialAttacks();
    }

}
