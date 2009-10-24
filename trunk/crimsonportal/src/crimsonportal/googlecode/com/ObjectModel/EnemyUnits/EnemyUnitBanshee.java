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
import crimsonportal.googlecode.com.ObjectModel.Weapon;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPistol;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPunch;

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
    
    public EnemyUnitBanshee(Location location, GameState gameState, GameObject target)
    {
        super(SIZE, location, gameState, DEFAULT_HEALTH, MOVE_SPEED, target);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitBanshee(location, gameState, strategy.getTarget());
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }

    public Weapon getDefaultWeapon() {
        return new WeaponPunch(ATTACK_DAMAGE, ATTACK_SPEED);
    }
    
    @Override
    protected MovementHandler getMovementHandler() {
        return new MovementHandlerStraightLine(MOVE_SPEED);
    }
    
}
