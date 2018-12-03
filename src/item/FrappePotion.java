package item;

public class FrappePotion extends HealthPotion{
    public FrappePotion(int num){
        super(num);
        setItemName("Frappe Potion");
        setHealthRestore(20);
    }
}
