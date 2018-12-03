package item;

import players.Player;

abstract public class ManaPotion extends Item{
    private double manaRestore;

    public ManaPotion(int num){
        super(num);
        setStackable(true);
    }

    public int use(Player user){
        double m = user.getManaLeft(), mP;
        user.setManaLeft(user.getManaLeft() + manaRestore);
        if (user.getManaLeft() > user.getMana()){
            user.setManaLeft(user.getMana());
        }
        mP = user.getManaLeft();
        System.out.println("You have regained " + (mP - m) + " Mana Points.");
        setStack(getStack() - 1);
        return 0;
    }

    protected void setManaRestore(double manaRestore){
        this.manaRestore = manaRestore;
    }
}
