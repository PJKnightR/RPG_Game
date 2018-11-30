package item;

public class GrandePotion extends HealthPotion{
    public static final byte ID = 5;
    public GrandePotion(int num){
        super(num);
        setItemName("Grande Potion");
        setValue(300);
        setHealthRestore(50);
    }
}
