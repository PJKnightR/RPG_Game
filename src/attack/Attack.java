package attack;

abstract public class Attack {
    protected String name, description;
    protected int power, manaCost;

    public String getAttackName(){
        return name;
    }

    public int getPower(){
        return power;
    }

    public String toString(){
        return(name);
    }

    public String getDescription(){
        return description;
    }

    public int getManaCost(){
        return manaCost;
    }

}
