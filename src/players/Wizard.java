package players;

import attack.FingerLightening;
import attack.Fireball;
import players.Player;
import java.util.ArrayList;


public class Wizard extends Player {

    public Wizard (String name){

        setName(name);
        setLevel(1);

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
        atts.add(new FingerLightening());

        attLevel = new int[]{1,5};
        getInitialAttacks();

    }
    public void setLevel(Player pl) { level = pl.getLevel(); }
}
