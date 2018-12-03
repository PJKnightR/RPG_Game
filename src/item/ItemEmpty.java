package item;

import players.Player;

public class ItemEmpty extends Item{
    public ItemEmpty(int num){
        super(num);
        setItemName("Error");
        setStackable(true);
    }

    public int use(Player user){
        return 0;
    }
}
