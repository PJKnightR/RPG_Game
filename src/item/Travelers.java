package item;

import item.Weapon;

public class Travelers extends Weapon {
    public Travelers(int num, String name){
        super(num, name);
        setItemName("Travelers " + name);
        setDamage(5);
        setID(10);
        setValue(100);
    }
}
