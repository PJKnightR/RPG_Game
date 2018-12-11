package item;

import item.Weapon;

public class Heros extends Weapon {
    public Heros(int num, String name){
        super(num, name);
        setItemName("Heros " + name);
        setDamage(75);
    }
}
