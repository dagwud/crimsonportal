/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crimsonportal.googlecode.com.Factories;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitBanshee;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitBarbarian;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitCritter;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitFletcher;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitLeech;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitLemmingLeader;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitScuttler;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitSuperCritter;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitZombie;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;

/**
 *
 * @author dagwud
 */
public class EnemyUnitFactory
{
    public static enum enemyType
    {
        ENEMY_BANSHEE,
        ENEMY_BARBARIAN,
        ENEMY_CRITTER,
        ENEMY_FLETCHER,
        ENEMY_LEECH,
        ENEMY_LEMMINGLEADER,
        ENEMY_SCUTTLER,
        ENEMY_SUPERCRITTER,
        ENEMY_ZOMBIE,
        ENEMY_LEMMING
    }
    
    public static int getEnemyLevelRequirement(enemyType enemyType) {
        switch (enemyType) {
            case ENEMY_BANSHEE:         return 1;
            case ENEMY_BARBARIAN:       return 2;
            case ENEMY_CRITTER:         return 3;
            case ENEMY_FLETCHER:        return 4;
            case ENEMY_LEECH:           return 4;
            case ENEMY_LEMMINGLEADER:   return 5;
            case ENEMY_SCUTTLER:        return 5;
            case ENEMY_SUPERCRITTER:    return 6;
            case ENEMY_ZOMBIE:          return 6;

            default:
                throw new UnsupportedOperationException("EnemyType " + enemyType + " not found");
        }
    }

    public static EnemyUnit[] createEnemyUnit(enemyType enemyType,
            Location location, GameObject target, GameState gameState)
    {
        switch (enemyType)
        {
            case ENEMY_BANSHEE:
                return new EnemyUnit[] {new EnemyUnitBanshee(location, gameState, target)};
            case ENEMY_BARBARIAN:
                return new EnemyUnit[] {new EnemyUnitBarbarian(location, gameState, target)};
            case ENEMY_CRITTER:
                return new EnemyUnit[] {new EnemyUnitCritter(location, gameState, target)};
            case ENEMY_FLETCHER:
                return new EnemyUnit[] {new EnemyUnitFletcher(location, gameState, target)};
            case ENEMY_LEECH:
                return new EnemyUnit[] {new EnemyUnitLeech(location, gameState, target)};
            case ENEMY_LEMMINGLEADER:
                EnemyUnitLemmingLeader leader = new EnemyUnitLemmingLeader(location, gameState, target);
                return leader.getLeaderAndLemmings();
            case ENEMY_SCUTTLER:
                return new EnemyUnit[] {new EnemyUnitScuttler(location, gameState, target)};
            case ENEMY_SUPERCRITTER:
                return new EnemyUnit[] {new EnemyUnitSuperCritter(location, gameState, target)};
            case ENEMY_ZOMBIE:
                return new EnemyUnit[] {new EnemyUnitZombie(location, gameState, target)};

            default:
                throw new UnsupportedOperationException("EnemyType " + enemyType + " not found");
        }
    }
}
