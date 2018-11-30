package item;

public class MongoPotion extends HealthPotion{
    public static byte ID = 6;
    public MongoPotion(int num){
        super(num);
        setItemName("Mongo Potion");
        setValue(1000);
        setHealthRestore(200);
    }
}
