package players;

import players.Player;
import java.util.ArrayList;


public class Knight extends Player {

    public Knight(Player pl){

        super(pl.name);

        attack = 70;
        baseDefense = 800;
        baseSpeed = 70;
        baseHealth = 80;
        baseMana = 70;

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
