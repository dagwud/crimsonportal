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
        ENEMY_ZOMBIE
    }

    public static EnemyUnit[] createEnemyUnit(enemyType enemyType,
            Location location, GameObject target, GameState gameState)
    {
        switch (enemyType)
        {
            case ENEMY_BANSHEE:
                return new EnemyUnit[] {new EnemyUnitBanshee(location, target, gameState)};
            case ENEMY_BARBARIAN:
                return new EnemyUnit[] {new EnemyUnitBarbarian(location, target, gameState)};
            case ENEMY_CRITTER:
                return new EnemyUnit[] {new EnemyUnitCritter(location, target, gameState)};
            case ENEMY_FLETCHER:
                return new EnemyUnit[] {new EnemyUnitFletcher(location, target, gameState)};
            case ENEMY_LEECH:
                return new EnemyUnit[] {new EnemyUnitLeech(location, target, gameState)};
            case ENEMY_LEMMINGLEADER:
                EnemyUnitLemmingLeader leader = new EnemyUnitLemmingLeader(location, target, gameState);
                return leader.getLeaderAndLemmings();
            case ENEMY_SCUTTLER:
                return new EnemyUnit[] {new EnemyUnitScuttler(location, target, gameState)};
            case ENEMY_SUPERCRITTER:
                return new EnemyUnit[] {new EnemyUnitSuperCritter(location, target, gameState)};
            case ENEMY_ZOMBIE:
                return new EnemyUnit[] {new EnemyUnitZombie(location, target, gameState)};

            default:
                throw new UnsupportedOperationException("EnemyType " + enemyType + " not found");
        }
    }
}