package players;

import attack.RiskyLunge;
import attack.SwordSlash;
import players.Player;
import java.util.ArrayList;


public class Knight extends Player {

    public Knight(String name){

        setName(name);

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


        atts = new ArrayList<>();
        att = new ArrayList<>();

        atts.add(new SwordSlash());
        atts.add(new RiskyLunge());

        attLevel = new int[]{1,1};
        getInitialAttacks();

    }
    public void setLevel(Player pl) { level = pl.getLevel(); }
}
