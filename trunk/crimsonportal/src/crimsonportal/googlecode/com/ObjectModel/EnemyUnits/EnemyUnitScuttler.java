/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;

/**
 *
 * @author dagwud
 */
public class EnemyUnitScuttler extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_HURT;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_WALK;
    protected static final double ROTATE_SPEED = Math.toRadians(10);
    protected static double DEFAULT_HEALTH = 10;
    protected static final MovementType MOVEMENT_TYPE = EnemyUnit.MovementType.MOVEMENT_STRAIGHTLINE;
    private static final String SPRITE_FILENAME = "enemy_scuttler.gif";
    
    public EnemyUnitScuttler(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target, gameState);
    }
    
    public MovementType getMovementType() {
        return MOVEMENT_TYPE;
    }
    
    @Override
    public void move() {
       // If the target is not in attacking range, rotate until the scuttler
       // is facing side-on to the target. If the scuttler is side-on, then move.
       
        GameObject target = getStrategy().getTarget();
        
        
        // Determine the options for rotating; either rotate clockwise (+90 deg) 
        // or counter-clockwise (-90 deg)
        double rotationToTarget = super.getRotation();
        double rotationCW = rotationToTarget + (Math.PI / 2.0);
        double rotationCCW = rotationToTarget - (Math.PI / 2.0);
        boolean closeEnoughToAttack = testOverlapsWith(target);
        double desiredRotation = 0;
        if (closeEnoughToAttack) {
            // Rotate towards the player:
            desiredRotation = rotationToTarget;
        } else {
            // Decide whether to rotate clockwise or counterclockwise (whichever
            // is closer to the current rotation)
            if (Math.abs(rotation - rotationCW) < Math.abs(rotation - rotationCCW)) { 
                desiredRotation = rotationCW;
            } else {
                desiredRotation = rotationCCW;
            }
        }
        
        // If already facing side-on to target; move towards the target:
        if (Math.abs(rotation - desiredRotation) < ROTATE_SPEED) {
            setRotation(desiredRotation);
            // If the scuttler is not already close enough to attack, move:
            if (! closeEnoughToAttack ) {
                super.move();
            }
        }
        
        // Turn towards the desired rotation:
        double newRotation;
        if (rotation > desiredRotation) {
            newRotation = rotation - ROTATE_SPEED;
        } else {
            newRotation = rotation + ROTATE_SPEED;
        }
        // If turning will bring the scuttler closer to it's desired rotation, 
        // rotate: (this prevents 'jittering')
        if (Math.abs(newRotation - desiredRotation) < Math.abs(rotation - desiredRotation)) {
            rotation = newRotation;
        }
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
}
