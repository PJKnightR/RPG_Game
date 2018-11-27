package item;

public class FrappePotion extends HealthPotion{
    public static final byte ID = 4;
    public FrappePotion(int num){
        super(num);
        setItemName("Frappe Potion");
        setValue(100);
        setHealthRestore(20);
    }
}
