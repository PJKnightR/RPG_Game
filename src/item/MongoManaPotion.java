package item;

public class MongoManaPotion extends ManaPotion {
    public MongoManaPotion(int num){
        super(num);
        setItemName("Mongo Mana Potion");
        setManaRestore(200);
    }
}
