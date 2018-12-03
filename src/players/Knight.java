package players;

import attack.RiskyLunge;
import attack.SpinningStrike;
import attack.SwordSlash;
import attack.WarriorsBeam;
import java.util.ArrayList;
import java.util.LinkedList;


public class Knight extends Player {

    public Knight(String name){

        setName(name);
        setLevel(100);

        baseAttack = 70;
        baseDefense = 80;
        baseSpeed = 70;
        baseHealth = 80;
        baseMana = 70;

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

        atts.add(new SwordSlash());
        atts.add(new SpinningStrike());
        atts.add(new RiskyLunge());
        atts.add(new WarriorsBeam());

        attLevel = new int[]{1,5,10,25};
        getInitialAttacks();

    }
    public void setLevel(Player pl) { level = pl.getLevel(); }
}
