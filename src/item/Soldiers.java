package item;

import item.Weapon;

public class Soldiers extends Weapon {
    public Soldiers(int num, String name){
        super(num, name);
        setItemName("Soldiers " + name);
        setDamage(20);
        setID(12);
    }
}
