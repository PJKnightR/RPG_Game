package players;

import attack.*;
import item.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Hunter extends Player{
    public Hunter(String name){

        setName(name);
        setLevel(1);
        classType = 4;
        weaponType = "Spear";
        equipped = new Wooden(1, weaponType);
        worn = new OldDirtyClothing(1);
        canister = new EmptyHeartCanister(1);

        baseAttack = 85;
        baseDefense = 65;
        baseSpeed = 90;
        baseHealth = 85;
        baseMana = 65;

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

        atts.add(new SpearChuck());
        atts.add(new Grenade());
        atts.add(new RiskyLunge());
        atts.add(new PiercingBlow());

        attLevel = new int[]{1,5,10,25};
        getInitialAttacks();

    }

    public Hunter(String name, double level, int gold, int exp, int w, int a, int curHealth, int curMana){

        setName(name);
        setLevel(level);
        this.gold = gold;
        setExp(exp);
        classType = 4;
        weaponType = "Spear";
        equipped = (Weapon) Weapon.itemListMaster(this.getWeaponType())[w];
        worn = (Armor) Armor.itemListMaster(this.getWeaponType())[a];
        canister = new EmptyHeartCanister(1);

        baseAttack = 70;
        baseDefense = 80;
        baseSpeed = 70;
        baseHealth = 80;
        baseMana = 70;

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

        atts.add(new SpearChuck());
        atts.add(new Grenade());
        atts.add(new RiskyLunge());
        atts.add(new PiercingBlow());

        attLevel = new int[]{1,5,10,25};
        getInitialAttacks();
    }

}
