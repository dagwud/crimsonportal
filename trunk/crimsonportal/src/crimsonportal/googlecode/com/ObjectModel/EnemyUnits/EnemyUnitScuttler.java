/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.MovementHandler;
import crimsonportal.googlecode.com.ObjectModel.MovementHandlerScuttle;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;

/**
 *
 * @author dagwud
 */
public class EnemyUnitScuttler extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_MEDIUM;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_HURT;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_WALK;
    protected static final double ROTATE_SPEED = Math.toRadians(10);
    protected static double DEFAULT_HEALTH = 10;
    private static final String SPRITE_FILENAME = "enemy_scuttler.gif";
    
    public EnemyUnitScuttler(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target, gameState);
    }
    
    
    @Override
    public void attack(PlayerUnit player) {
        double rotationToTarget = super.getRotation();
        double currentRotation = rotation;
        
        // If the enemy is facing the player, attack:
        if (Math.abs(currentRotation - rotationToTarget) < ROTATE_SPEED) {
            super.attack(player);
        } else {
            // Otherwise, don't attack - the enemy will turn during the move()
            // method, and will attack when it is facing the player
        }
    }
    
    @Override
    public double getRotation() {
        return rotation;
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitScuttler(location, strategy.getTarget(), gameState);
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }
    
    protected MovementHandler getMovementHandler() {
        return new MovementHandlerScuttle(MOVE_SPEED, ROTATE_SPEED);
    }
}
