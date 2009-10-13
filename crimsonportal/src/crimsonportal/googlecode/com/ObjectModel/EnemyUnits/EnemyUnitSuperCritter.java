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
import crimsonportal.googlecode.com.ObjectModel.MovementHandlerStraightLine;

/**
 *
 * @author dagwud
 */
public class EnemyUnitSuperCritter extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_MEDIUM;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_ANNOY;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_RACE;
    protected static double DEFAULT_HEALTH = 10;
    private static final String SPRITE_FILENAME = "enemy_supercritter.gif";
    
    public EnemyUnitSuperCritter(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target, gameState);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitSuperCritter(location, strategy.getTarget(), gameState);
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }

    @Override
    protected MovementHandler getMovementHandler() {
        return new MovementHandlerStraightLine(MOVE_SPEED);
    }
    
}
