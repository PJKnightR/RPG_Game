package item;

import players.Player;

abstract public class Weapon extends Item{ // extends Item
    private int damage;

    public Weapon(int num, String n){
        super(num);
        setStackable(true);
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int d){
        this.damage = d;
    }

    public int use(Player user){
        user.getInventory().addNewItem(user.getEquipped().getID(), user.getWeaponType());
        user.setEquipped(this);
        user.setAttack();
        System.out.println("You equipped the " + this.getItemName());
        setStack(getStack() - 1);
        return 0;
    }

}

