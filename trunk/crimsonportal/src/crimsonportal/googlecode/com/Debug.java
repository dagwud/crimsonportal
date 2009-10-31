/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

import crimsonportal.googlecode.com.Factories.EnemyUnitFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author dagwud
 */
public abstract class Debug 
{
    public static final int DEBUG_VERBOSE = 4;
    public static final int DEBUG_ERRORS = 3;
    public static final int DEBUG_WARNINGS = 2;
    public static final int DEBUG_INFO = 1;
    public static final int DEBUG_NONE = 0;
    
    public static final int enabledLevel = DEBUG_WARNINGS;
    
    public static synchronized void logMethod(String string)
    {
        if (enabledLevel < DEBUG_VERBOSE)
        {
            return;
        }
        System.out.println(string);
    }
    
    public static synchronized void logEvent(String string) {
        System.out.println(string);
    }
    
    public static synchronized void logWarning(String string)
    {
        if (enabledLevel < DEBUG_WARNINGS)
        {
            return;
        }
        System.err.println(string);
    }
    
    public static synchronized void print(String string)
    {
        if (enabledLevel < DEBUG_INFO) 
        {
            return;
        }
        System.out.print(string);
    }
    
    public static void setFlag(flagKey flag, flagValue value) {
        //if (getFlagValue(flag) != value) System.out.println(flag + " --> " + value);
        flags.put(flag, value);
    }
    
    public static flagValue getFlagValue(flagKey flag) {
        flagValue v = flags.get(flag);
        if (v == null) {
            v = Debug.flagValue.NOT_SET;
        }
        return v;
    }
    
    public static boolean checkFlag(flagKey flag) {
        flagValue v = getFlagValue(flag);
        if (v == flagValue.NOT_SET) return false;
        return (v == flagValue.TRUE);
    }
    
    public static void toggleFlag(flagKey flag) {
        if (checkFlag(flag)) {
            setFlag(flag, Debug.flagValue.FALSE);
        }
        else {
            setFlag(flag, Debug.flagValue.TRUE);
        }
    }
    
    public static void logAttack(EnemyUnitFactory.enemyType enemyType, int attackDamage) {
        // If the map hasn't been used before, initialise it:
        if (mapDamagerEnemies == null) {
            mapDamagerEnemies = new HashMap<EnemyUnitFactory.enemyType, Integer>();
        }
        
        // Get the total amount of damage done by this enemyType:
        Integer totalAttackDamage = mapDamagerEnemies.get(enemyType);
        if (totalAttackDamage == null) {
            // If this enemyType hasn't damaged the player before, initialise 
            // its counter:
            totalAttackDamage = 0;
        }
        
        // Increase the counter of the amount of damage done by this enemyType:
        totalAttackDamage += attackDamage;
        mapDamagerEnemies.put(enemyType, totalAttackDamage);
    }
    
    public static void logKill(EnemyUnitFactory.enemyType enemyType, int attackDamage) {
        // If the map hasn't been used before, initialise it:
        if (mapEnemiesKilled == null) {
            mapEnemiesKilled = new HashMap<EnemyUnitFactory.enemyType, Integer>();
        }
        
        // Get the total amount of damage done by this enemyType:
        Integer totalKills = mapEnemiesKilled.get(enemyType);
        if (totalKills == null) {
            // If the player hasn't killed this enemyType before, initialise 
            // its counter:
            totalKills = 0;
        }
        
        // Increase the counter of the amount of damage done by this enemyType:
        totalKills++;
        mapEnemiesKilled.put(enemyType, totalKills);
    }
    
    public static void printLogs() {
        if (mapDamagerEnemies == null || mapEnemiesKilled == null) {
            return;
        }
        Set<EnemyUnitFactory.enemyType> types = new HashSet<EnemyUnitFactory.enemyType>();
        if (mapDamagerEnemies != null) {
            mapDamagerEnemies.keySet();
        }
        if (mapEnemiesKilled != null) {
            types.addAll(mapEnemiesKilled.keySet());
        }
        
        Iterator<EnemyUnitFactory.enemyType> it = types.iterator();
        System.out.println("EnemyType, damage, kills");
        while (it.hasNext()) {
            EnemyUnitFactory.enemyType type = it.next();
            Integer damage = mapDamagerEnemies.get(type);
            if (damage == null) { 
                damage = 0; 
            }
            Integer kills = mapEnemiesKilled.get(type);
            if (kills == null) {
                kills = 0;
            }
            System.out.println(type.name() + ", " + damage + ", " + kills);
        }
    }
    
    private static Map<EnemyUnitFactory.enemyType, Integer> mapDamagerEnemies;
    private static Map<EnemyUnitFactory.enemyType, Integer> mapEnemiesKilled;
    
    protected static Map<flagKey, flagValue> flags;
    static {
        flags = new HashMap<Debug.flagKey, Debug.flagValue>();
    }
    
    public static enum flagKey {
        DISABLE_ENEMY_SPAWNING,
        PLAYER_MOVEMENT_VERTICAL,
        DISABLE_ENEMY_MOVEMENT,
        LOGATTACKDAMAGE
    };
    
    public static enum flagValue {
        TRUE,
        FALSE,
        
        ASCENDING,
        DESCENDING,
        LEVEL,
        
        NOT_SET
    };
}
