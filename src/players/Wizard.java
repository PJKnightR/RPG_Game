package players;

import attack.Fireball;
import players.Player;
import java.util.ArrayList;


public class Wizard extends Player {

    public Wizard (String name){

        setName(name);

        baseAttack = 90;
        baseDefense = 70;
        baseSpeed = 60;
        baseHealth = 100;
        baseMana = 90;

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

        atts.add(new Fireball());

        attLevel = new int[]{1};
        getInitialAttacks();

    }
    public void setLevel(Player pl) { level = pl.getLevel(); }
}
