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
import crimsonportal.googlecode.com.ObjectModel.MovementHandlerScuttle;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import crimsonportal.googlecode.com.ObjectModel.Unit;
import crimsonportal.googlecode.com.ObjectModel.Weapon;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPistol;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPunch;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponScuttler;

/**
 *
 * @author dagwud
 */
public class EnemyUnitScuttler extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_MEDIUM;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_HURT;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_WALK;
    protected static final double ROTATE_SPEED = Math.toRadians(10);
    protected static double DEFAULT_HEALTH = 10;
    private static final String SPRITE_FILENAME = "enemy_scuttler.gif";
    
    public EnemyUnitScuttler(Location location, GameState gameState, GameObject target)
    {
        super(SIZE, location, gameState, DEFAULT_HEALTH, MOVE_SPEED, target);
    }
    
    @Override
    public double getRotation() {
        return rotation;
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitScuttler(location, gameState, strategy.getTarget());
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }
    
    protected MovementHandler getMovementHandler() {
        return new MovementHandlerScuttle(MOVE_SPEED, ROTATE_SPEED);
    }
    
    @Override
    public void attack(Unit player) {
        double rotationToTarget = super.getRotation();
        double currentRotation = rotation;
        
        // If the enemy is facing the player, attack:
        if (Math.abs(currentRotation - rotationToTarget) < ROTATE_SPEED) {
            super.attack(player);
        } else {
            // Otherwise, don't attack - the enemy will turn during the move()
            // method, and will attack when it is facing the player
        }
    }
    
    public Weapon getDefaultWeapon() {
        return new WeaponPunch(ATTACK_DAMAGE, ATTACK_SPEED);
    }

    public EnemyUnitFactory.enemyType getEnemyTypeEnum() {
        return EnemyUnitFactory.enemyType.ENEMY_SCUTTLER;
    }
}
