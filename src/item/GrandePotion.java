package item;

public class GrandePotion extends HealthPotion{
    public GrandePotion(int num){
        super(num);
        setItemName("Grande Potion");
        setHealthRestore(50);
    }
}
