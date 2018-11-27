package item;

import players.Player;

public class HealthPotion extends Item{
    private double healthRestore;

    public HealthPotion(int num){
        super(num);
        setStackable(true);
    }

    public int use(Player user){
        double h = user.getHealth(), hM;
        user.setHealthLeft(user.getHealthLeft() + healthRestore);
        if (user.getHealthLeft() > user.getHealth()){
            user.setHealthLeft(user.getHealth());
        }
        hM = user.getHealth();
        System.out.println("You have regained " + (hM - h) + " HP.");
        setStack(getStack() - 1);
        return 0;
    }

    protected void setHealthRestore(double healthRestore){
        this.healthRestore = healthRestore;
    }
}
