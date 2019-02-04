package players;

import attack.FingerLightening;
import attack.Fireball;
import attack.PlasmaStrike;
import attack.TripleElementBlast;
import item.*;

import java.util.ArrayList;
import java.util.LinkedList;


public class Wizard extends Player {

    public Wizard (String name){

        setName(name);
        setLevel(1);
        classType = 3;
        weaponType = "Wand";
        equipped = new Wooden(1, weaponType);
        worn = new OldDirtyClothing(1);
        canister = new EmptyHeartCanister(1);
        hasPet = false;

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

    public Wizard(String name, double level, int gold, int exp, int w, int a, int c, int curHealth, int curMana){

        setName(name);
        setLevel(level);
        this.gold = gold;
        setExp(exp);
        classType = 3;
        weaponType = "Wand";
        equipped = (Weapon) Weapon.itemListMaster(this.getWeaponType())[w];
        worn = (Armor) Armor.itemListMaster(this.getWeaponType())[a];
        canister = (HeartCanister) HeartCanister.itemListMaster(this.getWeaponType())[c];
        hasPet = false;

        baseAttack = 90;
        baseDefense = 70;
        baseSpeed = 60;
        baseHealth = 100;
        baseMana = 90;

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

        atts.add(new Fireball());
        atts.add(new FingerLightening());
        atts.add(new PlasmaStrike());
        atts.add(new TripleElementBlast());

        attLevel = new int[]{1,5,10,25};
        getInitialAttacks();
    }

}
