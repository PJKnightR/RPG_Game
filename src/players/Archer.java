package players;

import players.Player;
import java.util.ArrayList;


public class Archer extends Player {

    public Archer (Player pl){

        super(pl.name);

        attack = 60;
        baseDefense = 50;
        baseSpeed = 90;
        baseHealth = 100;
        baseMana = 60;

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
