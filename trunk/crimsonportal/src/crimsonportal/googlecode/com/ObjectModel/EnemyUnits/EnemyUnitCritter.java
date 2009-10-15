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
import crimsonportal.googlecode.com.ObjectModel.MovementHandlerFly;

/**
 *
 * @author dagwud
 */
public class EnemyUnitCritter extends EnemyUnitFly {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_ANNOY;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_FAST;
    protected static final double TURN_SPEED = Math.toRadians(5);
    protected static final double MAX_TURN_RADIANS = Math.toRadians(90);
    protected static int MOVE_SPEED = PRESET_MOVESPEED_RACE;
    protected static double DEFAULT_HEALTH = 10;
    private static final String SPRITE_FILENAME = "enemy_critter.gif";
    
    public EnemyUnitCritter(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target, gameState);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitCritter(location, strategy.getTarget(), gameState);
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }

    public double getMaxTurnRadians() {
        return MAX_TURN_RADIANS;
    }
    
    public double getTurnSpeed() {
        return TURN_SPEED;
    }
    
    @Override
    protected MovementHandler getMovementHandler() {
        return new MovementHandlerFly(MOVE_SPEED, TURN_SPEED);
    }
}