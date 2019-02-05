package item;

import item.Weapon;

public class Legends extends Weapon {
    public Legends(int num, String name){
        super(num, name);
        setItemName("Legends " + name);
        setDamage(100);
        setID(16);
        setSellValue(400);
    }
}
