package players;

import attack.FingerLightening;
import attack.Fireball;
import attack.PlasmaStrike;
import attack.TripleElementBlast;
import players.Player;
import java.util.ArrayList;
import java.util.LinkedList;


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


        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new Fireball());
        atts.add(new FingerLightening());
        atts.add(new PlasmaStrike());
        atts.add(new TripleElementBlast());

        attLevel = new int[]{1,5,10,25};
        getInitialAttacks();

    }
    public void setLevel(Player pl) { level = pl.getLevel(); }
}
