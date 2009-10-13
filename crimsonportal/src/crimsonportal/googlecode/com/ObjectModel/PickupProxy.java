/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupArmour;
import crimsonportal.googlecode.com.ObjectModel.Weapons.UnitWithArmour;
import crimsonportal.googlecode.com.gui.Animation;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author dagwud
 */
public class PickupProxy {
    public static void setGameState(GameState gameState) {
        if (PickupProxy.gameState != null) {
            throw new IllegalArgumentException("PickupProxy has already had its " +
                    "gameState set");
        }
        PickupProxy.gameState = gameState;
    }
    
    public static void scaleUnitSize(Unit unit, double sizeScale) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        unit.setRadius(unit.getRadius() * sizeScale);
    }
    
    public static void scaleUnitSize(Collection<Unit> units, double sizeScale) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        Iterator<Unit> it_units = units.iterator();
        while (it_units.hasNext()) {
            scaleUnitSize(it_units.next(), sizeScale);
        }
    }
    
    public static void increaseUnitHealth(Unit unit, double healthValue) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        unit.setHealth(unit.getHealth() + healthValue);
    }
    
    public static void scaleUnitHealth(Collection<Unit> units, double healthValue) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        Iterator<Unit> it_units = units.iterator();
        while (it_units.hasNext()) {
            increaseUnitHealth(it_units.next(), healthValue);
        }
    }
    
    public static void scaleUnitMoveSpeed(Unit unit, double moveSpeedMultiplier) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        unit.getMovementHandler().setMoveSpeed(unit.getMovementHandler().getMoveSpeed() * moveSpeedMultiplier);
    }
    
    public static void scaleUnitMoveSpeed(Collection<Unit> units, double moveSpeedMultiplier) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        Iterator<Unit> it_units = units.iterator();
        while (it_units.hasNext()) {
            scaleUnitMoveSpeed(it_units.next(), moveSpeedMultiplier);
        }
    }
    
    public static void killUnit(Unit unit) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        gameState.killUnit(unit);
    }
    
    public static void killUnit(Collection<Unit> units) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        Iterator<Unit> it_units = units.iterator();
        while (it_units.hasNext()) {
            killUnit(it_units.next());
        }
    }
    
    public static void setUnitArmour(UnitWithArmour unit, PickupArmour armour) {
        unit.setArmourPercentage(100.0);
        unit.setArmourStrength(armour.getStrength());
    }
    
    /**
     * Returns an iterator which will iterate over all the EnemyUnits within a
     * given distance of a given location
     * @param location the location which is being used in comparison against
     * the enemy units' locations
     * @param rangeRadius the maximum distance from <code>location</code> which
     * returned enemies may be
     * @return an iterator over the collection of EnemyUnits which are within a
     * distance of <code>rangeRadius</code> of <code>location</code>
     * @see Location#getDistanceFrom
     */
    public static Iterator<EnemyUnit> getEnemiesNear(Location location, double rangeRadius) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        return gameState.getEnemiesNear(location, rangeRadius);
    }
    
    /**
     * Returns an iterator which will iterate over all the EnemyUnits within a
     * given distance of a given GameObject
     * @param object the object which is being used in comparison against the 
     * enemy units' locations
     * @param rangeRadius the maximum distance from <code>location</code> which
     * returned enemies may be
     * @return an iterator over the collection of EnemyUnits which are within a
     * distance of <code>rangeRadius</code> of <code>object</code>
     * @see Location#getDistanceFrom
     */
    public static Iterator<EnemyUnit> getEnemiesNear(GameObject object, double rangeRadius) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        return getEnemiesNear(object.getCentreOfObject(), rangeRadius);
    }
    
    public static void addAnimation(Animation animation) {
        if (gameState == null) throw new IllegalArgumentException(GAMESTATE_NOT_SET);
        gameState.addAnimation(animation);
    }
    
    private static GameState gameState;
    private static final String GAMESTATE_NOT_SET = 
            "PickupProxy.setGameState(GameState) has not yet been called";
}
