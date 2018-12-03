package item;

public class MongoPotion extends HealthPotion{
    public MongoPotion(int num){
        super(num);
        setItemName("Mongo Potion");
        setHealthRestore(200);
    }
}
