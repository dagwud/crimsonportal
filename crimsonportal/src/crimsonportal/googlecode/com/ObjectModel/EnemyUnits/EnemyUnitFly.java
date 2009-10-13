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
 * @author jdevenish
 */
public abstract class EnemyUnitFly extends EnemyUnit {
    public EnemyUnitFly(double size, double startingHealth, int attackDamage, 
            double attackSpeed, int moveSpeed, Location location, GameObject target, 
            GameState gameState) {
        super(size, startingHealth, attackDamage, attackSpeed, moveSpeed, location, target, gameState);
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
    
    protected boolean turningClockwise = true;
    protected double shiftAngleRadians = 0;
}
