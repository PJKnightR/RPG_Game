public class Battle {
    /**startBattle()
     * -Initializes values needed to start the battle. Will most likely be where the players current health is imported
     *
     * generateEnemy()
     * -may do this in a separate method somewhere, easier to implement things like bosses, may put in game (unless
     * fighting more than one enemy at a time is a thing). Will be using the players level in some fashion.
     *
     * playerMove()
     * -Where player chooses their move, this value will be sent to methods following this. This is also where items and
     * whatnot can be used during battle, and actions other than attacks will have their own specific numbers sent to
     * playerAttack() where that methods will determine if the player attack or did not. This will allow a smoother
     * implementation of being able to use items immediately
     *
     * enemyMove()
     * -Where enemy chooses their move, this value will be sent to methods following this. By implementing this as a
     * separate method this time, it could allow enemies to get an attack in before a player uses an item or runs if
     * attacks like that are implemented ie Pursuit from Pokemon. Maybe a weak "Quick Jab" move idk
     *
     * compareSpeeds()
     * -Figures out who will attack first by comparing the enemy and player speeds
     *
     * playerAttack()
     * -does the action of attacking the enemy and damage calculation. Will determine if the player even attacked or not.
     * This method will do nothing if the player did not choose an attack
     *
     * enemyAttack()
     * -does the action of attacking the player and damage calculation. Attacks for enemies and players should be stored
     * in arrayList. This will make them less buggy and prevent people from using attacks they don't have/don't exist.
     *
     * displayHealth()
     * -displays the current health of the player and the enemy
     *
     * checkPlayerLose()
     * -makes sure the player is still alive. If not then game over.
     *
     * checkEnemyLose()
     * -makes sure the enemy is still alive. Deals out experience, possible items, ect. Will partially handle level ups.
     *
     * getCriticalHitModifier()
     * -determines if the attack is critical or not
     */
}
