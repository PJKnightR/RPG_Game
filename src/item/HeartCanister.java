package item;

import players.Player;

public class HeartCanister extends Item{
    private int healthBoost;

    public HeartCanister(int num){
        super(num);
        setStackable(true);
    }

    public int getHealthBoost() {
        return this.healthBoost;
    }

    public void setHealthBoost(int p){
        this.healthBoost = p;
    }

    public int use(Player user){
        user.getInventory().addNewItem(user.canister);
        user.setCanister(this);
        user.setHealth();
        System.out.println("You equipped the " + this.getItemName());
        setStack(getStack() - 1);
        return 0;
    }
}
