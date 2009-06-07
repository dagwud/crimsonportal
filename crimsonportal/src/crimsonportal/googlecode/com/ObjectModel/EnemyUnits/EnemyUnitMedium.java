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
public class EnemyUnitMedium extends EnemyUnit {
    protected static final double SIZE = 40d;
    protected static final int ATTACK_DAMAGE = 1;
    protected static final double ATTACK_SPEED = 1.5;
    protected static int MOVE_SPEED = 3;
    protected static double DEFAULT_HEALTH = 4;
    private static final String SPRITE_FILENAME = "enemy.gif";
    
    public EnemyUnitMedium(Location location, GameObject target)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, target);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitMedium(location, strategy.getTarget());
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }
    
}
