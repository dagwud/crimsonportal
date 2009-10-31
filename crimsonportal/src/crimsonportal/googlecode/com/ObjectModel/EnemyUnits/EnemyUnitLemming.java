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
import crimsonportal.googlecode.com.ObjectModel.MovementHandlerStraightLine;
import crimsonportal.googlecode.com.ObjectModel.Weapon;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPunch;

/**
 *
 * @author dagwud
 */
public class EnemyUnitLemming extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_HIT;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_TROT;
    protected static double DEFAULT_HEALTH = 10;
    private static final String SPRITE_FILENAME = "enemy_lemming.gif";
    
    public EnemyUnitLemming(Location location, GameObject target, GameState gameState,
            EnemyUnitLemmingLeader leader) {
        super(SIZE, location, gameState, DEFAULT_HEALTH, MOVE_SPEED, target);
        this.leader = leader;
        this.setStrategy(new StrategyLemming(target, leader));
    }
    
    public EnemyUnitLemming(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, location, gameState, DEFAULT_HEALTH, MOVE_SPEED, target);
    }
        
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitLemming(location, strategy.getTarget(), gameState);
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }

    @Override
    protected MovementHandlerStraightLine getMovementHandler() {
        return new MovementHandlerStraightLine(MOVE_SPEED);
    }
    
    public Weapon getDefaultWeapon() {
        return new WeaponPunch(ATTACK_DAMAGE, ATTACK_SPEED);
    }
    
    protected EnemyUnitLemmingLeader leader;

    @Override
    public EnemyUnitFactory.enemyType getEnemyTypeEnum() {
        return EnemyUnitFactory.enemyType.ENEMY_LEMMING;
    }
}
