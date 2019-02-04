package players;

import attack.RiskyLunge;
import item.CrystalHeartCanister;
import item.Legends;
import item.SilverPlatemail;

import java.util.ArrayList;
import java.util.LinkedList;

public class RiskyLungeKLnight extends Player{

    public RiskyLungeKLnight(String name){
        System.out.println("Bravely (or foolishly), you have re-awoken an ancient, unknown evil.");

        setName(name);
        setLevel(1);
        classType = 2;
        weaponType = "Risky Sword";
        equipped = new Legends(1, weaponType);
        worn = new SilverPlatemail(1);
        canister = new CrystalHeartCanister(1);
        hasPet = false;

        baseAttack = 9001;
        baseDefense = 9001;
        baseSpeed = 9001;
        baseHealth = 9001;
        baseMana = 9001;

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

        atts.add(new RiskyLunge());

        attLevel = new int[]{1};
        getInitialAttacks();
    }
}
