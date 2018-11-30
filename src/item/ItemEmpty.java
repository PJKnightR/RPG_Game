package item;

import players.Player;

public class ItemEmpty extends Item{
    public ItemEmpty(int num){
        super(num);
        setItemName("Error");
        setValue(1000000000);
        setStackable(true);
    }

    public int use(Player user){
        return 0;
    }
}
