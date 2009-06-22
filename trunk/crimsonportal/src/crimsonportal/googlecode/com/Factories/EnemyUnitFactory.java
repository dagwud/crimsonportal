/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Factories;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitLarge;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitMedium;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitSmall;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitTiny;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;

/**
 *
 * @author dagwud
 */
public class EnemyUnitFactory {
    public static enum enemyType {
        ENEMY_TINY,
        ENEMY_SMALL,
        ENEMY_MEDIUM,
        ENEMY_LARGE
    }
    
    public static EnemyUnit createEnemyUnit(enemyType enemyType, 
            Location location, GameObject target, GameState gameState) {
        switch (enemyType) {
            case ENEMY_TINY:
                return new EnemyUnitTiny(location, target, gameState);
            case ENEMY_SMALL:
                return new EnemyUnitSmall(location, target, gameState);
            case ENEMY_MEDIUM:
                return new EnemyUnitMedium(location, target, gameState);
            case ENEMY_LARGE:
                return new EnemyUnitLarge(location, target, gameState);
            default:
                throw new UnsupportedOperationException("EnemyType " + enemyType + " not found");
        }
    }
}
