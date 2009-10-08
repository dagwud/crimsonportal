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
        double rot = super.getRotation();
        double stumbleDurationMS = 700;
        double stumbleAngleDegrees = 10;
        double ms = System.currentTimeMillis() + msOffset;
        double p = (ms % stumbleDurationMS) / stumbleDurationMS;
        rotationShift = (Math.toRadians(stumbleAngleDegrees) * p);
        
        if (p < 0.5)
            rot = rot + (Math.toRadians(stumbleAngleDegrees)) - rotationShift;
        else
            rot = rot - (Math.toRadians(stumbleAngleDegrees)) + rotationShift;
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
