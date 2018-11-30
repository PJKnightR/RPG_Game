package item;

public class GarlicBread extends HealthPotion{
    public GarlicBread(int num){
        super(num);
        setItemName("Garlic Bread");
        setValue(200);
        setHealthRestore(35);
    }
}
