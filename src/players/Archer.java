package players;

import attack.ArrowStorm;
import attack.ArrowVolley;
import attack.FlamingArrows;
import attack.PiercingShots;
import item.*;

import java.util.ArrayList;
import java.util.LinkedList;


public class Archer extends Player {

    public Archer (String name){

        setName(name);
        setLevel(1);
        classType = 1;
        weaponType = "Bow";
        equipped = new Wooden(1, weaponType);
        worn = new OldDirtyClothing(1);
        canister = new EmptyHeartCanister(1);

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


        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new ArrowVolley());
        atts.add(new FlamingArrows());
        atts.add(new PiercingShots());
        atts.add(new ArrowStorm());

        attLevel = new int[]{1,5,10,25};
        getInitialAttacks();

    }

    public Archer(String name, double level, int gold, int exp, int w, int a, int curHealth, int curMana){

        setName(name);
        setLevel(level);
        this.gold = gold;
        setExp(exp);
        classType = 1;
        weaponType = "Bow";
        equipped = (Weapon) Weapon.itemListMaster(this.getWeaponType())[w];
        worn = (Armor) Armor.itemListMaster(this.getWeaponType())[a];
        canister = new EmptyHeartCanister(1);

        baseAttack = 60;
        baseDefense = 50;
        baseSpeed = 90;
        baseHealth = 100;
        baseMana = 60;

        setAttack();
        setDefense();
        setSpeed();
        setHealth();
        setHealthLeft(curHealth);
        setMana();
        setManaLeft(curMana);
        setExp(0);


        atts = new LinkedList<>();
        att = new ArrayList<>();

        atts.add(new ArrowVolley());
        atts.add(new FlamingArrows());
        atts.add(new PiercingShots());
        atts.add(new ArrowStorm());

        attLevel = new int[]{1,5,10,25};
        getInitialAttacks();
    }

}
