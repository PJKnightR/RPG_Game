package item;

import item.ManaPotion;

public class FrappeManaPotion extends ManaPotion {
    public FrappeManaPotion(int num){
        super(num);
        setItemName("Frappe Mana Potion");
        setManaRestore(20);
        setValue(250);
        setID(4);
        setSellValue(125);
    }
}
