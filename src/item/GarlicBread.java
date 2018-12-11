package item;

import item.HealthPotion;

public class GarlicBread extends HealthPotion {
    public GarlicBread(int num){
        super(num);
        setItemName("Garlic Bread");
        setHealthRestore(35);
        setValue(750);
    }
}
