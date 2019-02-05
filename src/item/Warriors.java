package item;

import item.Weapon;

public class Warriors extends Weapon {
    public Warriors(int num, String name){
        super(num, name);
        setItemName("Warriors " + name);
        setDamage(25);
        setID(13);
        setSellValue(250);
    }
}
