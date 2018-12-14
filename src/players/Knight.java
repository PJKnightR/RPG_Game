package players;

import attack.RiskyLunge;
import attack.SpinningStrike;
import attack.SwordSlash;
import attack.WarriorsBeam;
import item.*;

import java.util.ArrayList;
import java.util.LinkedList;


public class Knight extends Player {

    public Knight(String name){

        setName(name);
        setLevel(1);
        classType = 2;
        weaponType = "Sword";
        equipped = new Wooden(1, weaponType);
        worn = new OldDirtyClothing(1);
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

    public Knight(String name, double level, int gold, int exp, int w, int a, int curHealth, int curMana){

        setName(name);
        setLevel(level);
        this.gold = gold;
        setExp(exp);
        classType = 2;
        weaponType = "Sword";
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

        atts.add(new SwordSlash());
        atts.add(new SpinningStrike());
        atts.add(new RiskyLunge());
        atts.add(new WarriorsBeam());

        attLevel = new int[]{1,5,10,25};
        getInitialAttacks();
    }

}
