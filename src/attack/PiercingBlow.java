package attack;

public class PiercingBlow extends Attack{
    public PiercingBlow(){
        name = "Piercing Blow";
        power = 150;
        description = "The user precisely strikes their opponents vitals, doing great damage.";
        manaCost = 20;
        poisonChance = 0;
        paralyzChance = 0;
        petrifyChance = 40;
    }
}
