package item;

import item.HealthPotion;

public class GrandePotion extends HealthPotion {
    public GrandePotion(int num){
        super(num);
        setItemName("Grande Potion");
        setHealthRestore(50);
        setValue(500);
        setID(2);
        setSellValue(250);
    }
}
