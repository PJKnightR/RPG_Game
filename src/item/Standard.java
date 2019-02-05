package item;

import item.Weapon;

public class Standard extends Weapon {
    public Standard(int num, String name){
        super(num, name);
        setItemName("Standard " + name);
        setDamage(10);
        setID(11);
        setSellValue(150);
    }
}
