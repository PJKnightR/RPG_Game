package pets;

import game.Entity;

public class Pet extends Entity {
    public boolean fainted;
    public String nickname;
    protected int Exp;

    /**
     * Sets the health of the pet
     */
    public void setHealth(){
        health = (int)(((baseHealth + 31 + level * 4) / 4) * level / 100 + 10);
    }

    /**
     * Sets the defense stat of the pet
     */
    public void setDefense(){
        defense = (int)(((baseDefense + 31 + level * 4) / 4) * level / 100 + 5);
    }

    /**
     * Sets the attack stat of the pet
     */
    public void setAttack(){
        attack = (int)(((baseAttack + 31 + level * 4) / 4) * level / 100 + 5);
    }

    public void setHealthLeft(int h){
        healthLeft = h;
    }

    /**
     * Set the speed of the pet
     */
    public void setSpeed() {
        speed = (int)(((baseSpeed + 31 + level * 4) / 4) * level / 100 + 5);;
    }

    public void setNickname(String s) {
        nickname = s;
    }

    public String getNickname(){
        return nickname;
    }

    public boolean isFainted(){
        return fainted;
    }

    public void gainExp(int Experience){
        Exp += Experience;
        System.out.print("Your " + this.getNickname() + " gained " + Experience + " experience points! ");
    }


    public int getExp(){ return Exp; }


    public void setExp(int i){
        this.Exp = i;
    }

    public boolean checkLevelUp(){
        if(this.getExp() >= (100 * this.level)){
            return true;
        }
        return false;
    }

    public void levelUp(){
        this.setLevel(this.getLevel() + 1);
        System.out.println("Level Up!!! Your " + this.getNickname() + " has reached level " + this.getLevel() + "!");
        double e = 100 * this.level - getExp();
        setExp((int) e);
        this.setName(name);
        this.setAttack();
        this.setDefense();
        this.setSpeed();
        this.setHealth();
        this.setHealthLeft(health);
    }
}
