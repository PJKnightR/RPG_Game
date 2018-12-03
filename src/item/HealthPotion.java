package item;

import players.Player;

abstract public class HealthPotion extends Item{
    private double healthRestore;

    public HealthPotion(int num){
        super(num);
        setStackable(true);
    }

    public int use(Player user){
        double h = user.getHealthLeft(), hM;
        user.setHealthLeft(user.getHealthLeft() + healthRestore);
        if (user.getHealthLeft() > user.getHealth()){
            user.setHealthLeft(user.getHealth());
        }
        hM = user.getHealthLeft();
        System.out.println("You have regained " + (hM - h) + " Health Points.");
        setStack(getStack() - 1);
        return 0;
    }

    protected void setHealthRestore(double healthRestore){
        this.healthRestore = healthRestore;
    }
}
