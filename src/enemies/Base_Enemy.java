package enemies;

import game.Player;
import java.util.concurrent.ThreadLocalRandom;

public class Base_Enemy extends Enemy {

    private String diff;
    private Player player;

    public Base_Enemy() {
        diff = "easy";
        name = "Goblin";
        player = new Player(getName());
    }

    // Should set their health to 80% of player's health
    public void setHealth() {
        health = Math.round(player.getHealth()-(player.getHealth() * .2));
    }


    // Should set their health to 40%-60% of Player's defense
    public void setDefense() {
        double mod = ThreadLocalRandom.current().nextDouble(.40, .60);
        // This should round the enemy defense to a whole double that is 40%-60% of the Player's level.
        defense = Math.round(player.getDefense()-(player.getDefense()*mod));
    }


    // Should set attack to between 40%-60% of Player's attack
    public void setAttack() {
        double mod = ThreadLocalRandom.current().nextDouble(.40, .60);
        // This should round the enemy attack to a whole double that is 40%-60% of the Player's level.
        attack = Math.round(player.getAttack()-(player.getAttack()*mod));
    }


    // Should set the enemy level based on the player level
    public void setLevel() {
        double mod = ThreadLocalRandom.current().nextDouble(.30, .60);
        // This should round the enemy level to a whole double that is 40%-70% of the Player's level.
        level = Math.round(player.getLevel()-(player.getLevel()*mod));
    }

}
