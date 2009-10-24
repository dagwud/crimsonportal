/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.Weapon;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPistol;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPunch;

/**
 *
 * @author jdevenish
 */
public abstract class EnemyUnitFly extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_MEDIUM;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_ANNOY;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static double DEFAULT_HEALTH = 10;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_RACE;
    
    public EnemyUnitFly(Location location, GameState gameState, 
            GameObject target) {
        super(SIZE, location, gameState, DEFAULT_HEALTH, MOVE_SPEED, target);
    }
    
    public boolean isTurningClockwise() {
        return turningClockwise;
    }
    
    public void setTurningClockwise(boolean isTurningClockwise) {
        turningClockwise = isTurningClockwise;
    }
    
    public double getShiftAngleRadians() {
        return shiftAngleRadians;
    }
    
    public void setShiftAngleRadians(double shiftAngleRadians) {
        this.shiftAngleRadians = shiftAngleRadians;
    }
    
    @Override
    public double getRotation() {
        return super.getRotation() + shiftAngleRadians;
    }
        
    public Weapon getDefaultWeapon() {
        return new WeaponPunch(ATTACK_DAMAGE, ATTACK_SPEED);
    }

    public abstract double getMaxTurnRadians();
    public abstract double getTurnSpeed();
    
    protected boolean turningClockwise = true;
    protected double shiftAngleRadians = 0;
}
