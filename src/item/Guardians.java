package item;

import item.Weapon;

public class Guardians extends Weapon {
    public Guardians(int num, String name){
        super(num, name);
        setItemName("Guardians " + name);
        setDamage(50);
        setID(14);
        setSellValue(300);
    }
}
