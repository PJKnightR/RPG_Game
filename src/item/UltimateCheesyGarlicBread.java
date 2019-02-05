package item;

import item.HealthPotion;

public class UltimateCheesyGarlicBread extends HealthPotion {
    public UltimateCheesyGarlicBread(int num){
        super(num);
        setItemName("Ultimate Cheesy Garlic Bread");
        setHealthRestore(1000000);
        setID(8);
        setSellValue(500);
    }
}
