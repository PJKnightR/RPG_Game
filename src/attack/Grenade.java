package attack;

public class Grenade extends Attack{
    public Grenade(){
        name = "Grenade";
        power = 75;
        description = "The user throws a grenade that explodes on their opponents.";
        manaCost = 5;
        poisonChance = 0;
        paralyzChance = 0;
        petrifyChance = 0;
    }
}
