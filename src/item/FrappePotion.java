package item;

import item.HealthPotion;

public class FrappePotion extends HealthPotion {
    public FrappePotion(int num){
        super(num);
        setItemName("Frappe Potion");
        setHealthRestore(20);
        setValue(200);
        setID(1);
        setSellValue(100);
    }
}
