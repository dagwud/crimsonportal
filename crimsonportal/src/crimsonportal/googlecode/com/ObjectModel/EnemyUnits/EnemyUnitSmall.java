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
public class EnemyUnitSmall extends EnemyUnit {
    protected static final double SIZE = 30d;
    protected static final double ATTACK_SPEED = 2;
    protected static final int ATTACK_DAMAGE = 1;
    protected static int MOVE_SPEED = 2;
    protected static double DEFAULT_HEALTH = 2;
    private static final String SPRITE_FILENAME = "enemy.gif";
    
    public EnemyUnitSmall(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target, gameState);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitSmall(location, strategy.getTarget(), gameState);
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }
    
}
