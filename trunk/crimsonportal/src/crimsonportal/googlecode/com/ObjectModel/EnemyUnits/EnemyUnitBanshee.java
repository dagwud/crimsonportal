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
public class EnemyUnitBanshee extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_ANNOY;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_SLOW;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_WALK;
    protected static double DEFAULT_HEALTH = 10;
    private static final String SPRITE_FILENAME = "enemy_banshee.gif";
    
    public EnemyUnitBanshee(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target, gameState);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitBanshee(location, strategy.getTarget(), gameState);
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }
    
}
