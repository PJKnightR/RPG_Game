package players;

import attack.ArrowVolley;
import players.Player;
import java.util.ArrayList;


public class Archer extends Player {

    public Archer (String name){

        setName(name);
        setLevel(1);

        baseAttack = 60;
        baseDefense = 50;
        baseSpeed = 90;
        baseHealth = 100;
        baseMana = 60;

        setAttack();
        setDefense();
        setSpeed();
        setHealth();
        setHealthLeft(health);
        setMana();
        setManaLeft(mana);
        setExp(0);


        atts = new ArrayList<>();
        att = new ArrayList<>();

        atts.add(new ArrowVolley());

        attLevel = new int[]{1};
        getInitialAttacks();

    }
    public void setLevel(Player pl) { level = pl.getLevel(); }
}
