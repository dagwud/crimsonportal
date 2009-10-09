/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import java.util.Random;

/**
 *
 * @author dagwud
 */
public class EnemyUnitZombie extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_HIT;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_AMBLE;
    protected static double DEFAULT_HEALTH = 10;
    private static final String SPRITE_FILENAME = "enemy_zombie.gif";
    
    public EnemyUnitZombie(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target, gameState);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitZombie(location, strategy.getTarget(), gameState);
    }

    @Override
    public double getRotation()
    {
        // Get the direction from the zombie to its target:
        double rot = super.getRotation(); 
        
        // Zombies walk while rotating slightly, giving the impression of ambling.
        // The following settings define the style of the rotation.
        double stumbleDurationMS = 4000; // Duration of one rotation, in millisecs
        double stumbleAngleDegrees = 60; // The angle of rotation, in degrees
        
        // Determine the current percentage of the rotation, according to the time
        // that has elapsed compared to the duration of rotation. Use msOffset
        // to randomise the zombie a bit, so that zombies rotate independantly of
        // one another.
        double ms = (System.currentTimeMillis() + msOffset) % stumbleDurationMS;
        double perc = (ms / stumbleDurationMS);
        
        // Determine the amount of shift to add to the zombie's base rotation. This
        // will be an angle (in radians) in the range [0; stumbleAngle/2]
        rotationShift = (((perc * 100.0) % 25.0) / 25.0) * (Math.toRadians(stumbleAngleDegrees) / 2.0);
        
        // Rotate the zombie to make him seem to be ambling:
        // This is done in four phases:
        //   Phase 1 (p < 0.25): rotate clockwise away from the target
        //   Phase 2 (0.25 <= p < 0.5): rotate anticlockwise towards the target
        //   Phase 3 (0.5 <= p < 0.75): rotate anticlockwise away from the target
        //   Phase 4 (0.75 <= p < 1): rotate clockwise towards the target
        if (perc < 0.25) {
            rot = rot - (Math.toRadians(stumbleAngleDegrees / 2.0)) + rotationShift;
        }
        else if (perc < 0.5) {
            rot = rot + rotationShift;
        }
        else if (perc < 0.75) {
            rot = rot + (Math.toRadians(stumbleAngleDegrees / 2.0)) - rotationShift;
        }
        else {
            rot = rot - rotationShift;
        }
        return rot;
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }
    
    protected double rotationShift = 0.0;
    protected double msOffset = new Random().nextDouble() * 1000;
}
