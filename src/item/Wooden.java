package item;

import item.Weapon;

public class Wooden extends Weapon {
    public Wooden(int num, String name){
        super(num, name);
        setItemName("Wooden " + name);
        setDamage(1);
        setID(9);
    }
}
