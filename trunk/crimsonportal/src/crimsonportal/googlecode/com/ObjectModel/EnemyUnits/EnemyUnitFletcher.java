/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.Factories.EnemyUnitFactory;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.MovementHandler;
import crimsonportal.googlecode.com.ObjectModel.MovementHandlerAttackAndRetreat;
import crimsonportal.googlecode.com.ObjectModel.Weapon;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPistol;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPunch;

/**
 *
 * @author dagwud
 */
public class EnemyUnitFletcher extends EnemyUnitAttackAndRetreat {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_HURT;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_TROT;
    protected static int RETREAT_DISTANCE = 10;
    protected static double DEFAULT_HEALTH = 10;
    private static final String SPRITE_FILENAME = "enemy_fletcher.gif";
    
    public EnemyUnitFletcher() {
        super();
    }
    
    public EnemyUnitFletcher(Location location, GameState gameState, GameObject target)
    {
        super(SIZE, location, gameState, target, DEFAULT_HEALTH, MOVE_SPEED, 
                RETREAT_DISTANCE);
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitFletcher(location, gameState, strategy.getTarget());
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }

    @Override
    protected MovementHandler getMovementHandler() {
        return new MovementHandlerAttackAndRetreat(MOVE_SPEED);
    }    
    
    public Weapon getDefaultWeapon() {
        return new WeaponPunch(ATTACK_DAMAGE, ATTACK_SPEED);
    }

    @Override
    public EnemyUnitFactory.enemyType getEnemyTypeEnum() {
        return EnemyUnitFactory.enemyType.ENEMY_FLETCHER;
    }
}
