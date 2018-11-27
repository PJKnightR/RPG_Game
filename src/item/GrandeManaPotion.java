package item;

public class GrandeManaPotion extends ManaPotion{
    public static final byte ID = 10;
    public GrandeManaPotion(int num){
        super(num);
        setItemName("Grande Potion");
        setValue(350);
        setManaRestore(50);
    }
}
