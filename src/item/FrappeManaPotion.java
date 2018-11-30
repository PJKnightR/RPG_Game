package item;

public class FrappeManaPotion extends ManaPotion{
    public static final byte ID = 8;
    public FrappeManaPotion(int num){
        super(num);
        setItemName("Frappe Mana Potion");
        setValue(150);
        setManaRestore(20);
    }
}
