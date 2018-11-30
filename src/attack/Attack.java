package attack;

abstract public class Attack {
    protected String name, description;
    //protected boolean physical; damage types??
    protected int power, accuracy, manaCost;

    public String getAttackName(){
        return name;
    }

    //public boolean isPhysical(){
        //return physical;
    //}

    public int getPower(){
        return power;
    }

    public String toString(){
        return(name);
    }

    public String getDescription(){
        return description;
    }

    public int getAccuracy(){
        return accuracy;
    }

    public int getManaCost(){
        return manaCost;
    }

}
