package item;

import item.ManaPotion;

public class GrandeManaPotion extends ManaPotion {
    public GrandeManaPotion(int num){
        super(num);
        setItemName("Grande Mana Potion");
        setManaRestore(50);
        setValue(550);
    }
}
