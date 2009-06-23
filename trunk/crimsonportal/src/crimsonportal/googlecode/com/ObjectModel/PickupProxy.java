/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class PickupProxy {
    public static void scaleUnitSize(Unit unit, double sizeScale) {
        unit.setSize(unit.getSize() * sizeScale);
    }
    
    public static void increaseUnitHealth(Unit unit, double healthValue) {
        unit.setHealth(unit.getHealth() + healthValue);
    }
    
    public static void scaleUnitMoveSpeed(Unit unit, double moveSpeedMultiplier) {
        unit.setMoveSpeed(unit.getMoveSpeed() * moveSpeedMultiplier);
    }
}
