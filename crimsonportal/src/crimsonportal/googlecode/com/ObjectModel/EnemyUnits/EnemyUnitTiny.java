/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.Location;

/**
 *
 * @author dagwud
 */
public class EnemyUnitTiny extends EnemyUnit {
    protected static final double SIZE = 10d;
    protected static final int ATTACK_DAMAGE = 1;
    protected static final double ATTACK_SPEED = 3;
    protected static int MOVE_SPEED = 1;
    protected static double DEFAULT_HEALTH = 1;
    private static final String SPRITE_FILENAME = "enemy.gif";
    
    public EnemyUnitTiny(Location location, GameObject target)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitTiny(location, strategy.getTarget());
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }
    
}
