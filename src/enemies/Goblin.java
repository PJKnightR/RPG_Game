package enemies;

import attack.genericAttack1;
import attack.genericAttack2;
import game.Player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Goblin extends Enemy {

    // Indicates the enemy's difficulty
    private String diff;
    private Player player;

    public Goblin() {
        diff = "easy";
        name = "Goblin";
        player = new Player(getName());
        defense = 0;
        health_left = 100;
        atts = new ArrayList<>();
        att = new ArrayList<>();

        atts.add(new genericAttack1());
        atts.add(new genericAttack2());

        attLevel = new int[]{1,20};

        getInitialAttacks();
    }

    // Should set their health to 70%-80% of player's health
    double mod = ThreadLocalRandom.current().nextDouble(.30, .20);
    public void setHealth() {
        health = Math.round(player.getHealth()-(player.getHealth() * mod));
    }


    // Should set their defense to 50%-70% of Player's defense
    public void setDefense() {
        double mod = ThreadLocalRandom.current().nextDouble(.50, .70);
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
        // This should round the enemy level to a whole double that is 30%-60% of the Player's level.
        level = Math.round(player.getLevel()-(player.getLevel()*mod));
    }

}
