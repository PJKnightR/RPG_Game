package item;

import players.Player;

abstract public class Armor extends Item{
    private int protection;

    public Armor(int num){
        super(num);
        setStackable(true);
    }

    public int getProtection() {
        return this.protection;
    }

    public void setProtection(int p){
        this.protection = p;
    }

    public int use(Player user){
        user.getInventory().addNewItem(user.getWorn().getID(), user.getWeaponType());
        user.setWorn(this);
        user.setDefense();
        System.out.println("You equipped the " + this.getItemName());
        setStack(getStack() - 1);
        return 0;
    }
}
