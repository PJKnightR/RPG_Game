package players;

import players.Player;
import java.util.ArrayList;


public class Wizard extends Player {

    public Wizard (Player pl){

        super(pl.name);

        attack = 90;
        baseDefense = 70;
        baseSpeed = 60;
        baseHealth = 100;
        baseMana = 90;

        setLevel();
        setAttack();
        setDefense();
        setHealth();
        setMana();
        setHealthLeft(health);
        setSpeed();


        atts = new ArrayList<>();
        att = new ArrayList<>();


        attLevel = new int[]{1,1};
        getInitialAttacks();

    }
    public void setLevel(Player pl) { level = pl.getLevel(); }
}
