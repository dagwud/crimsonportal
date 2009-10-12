/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;

/**
 *
 * @author dagwud
 */
public class EnemyUnitCritter extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_ANNOY;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_FAST;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_WALK;
    protected static double DEFAULT_HEALTH = 10;
    protected static final MovementType MOVEMENT_TYPE = EnemyUnit.MovementType.MOVEMENT_STRAIGHTLINE;
    private static final String SPRITE_FILENAME = "enemy_critter.gif";
    
    public EnemyUnitCritter(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target, gameState);
    }
    
    public MovementType getMovementType() {
        return MOVEMENT_TYPE;
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
    
}
