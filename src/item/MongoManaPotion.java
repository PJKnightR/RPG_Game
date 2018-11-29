package item;

public class MongoManaPotion extends ManaPotion {
    public static byte ID = 6;
    public MongoManaPotion(int num){
        super(num);
        setItemName("Mongo Mana Potion");
        setValue(1200);
        setManaRestore(200);
    }
}
