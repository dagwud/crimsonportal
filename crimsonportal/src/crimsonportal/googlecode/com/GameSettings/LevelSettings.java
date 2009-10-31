/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.GameSettings;

import crimsonportal.googlecode.com.Factories.EnemyUnitFactory;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitBanshee;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitBarbarian;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitCritter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dagwud
 */
public class LevelSettings {
    public static int getMinEnemiesForLevel(int level) {
        Integer minEnemies = maxEnemiesPerLevel.get(level - 1);
        if (minEnemies == null) {
            return 1;
        }
        return minEnemies;
    }
    
    public static int getMaxEnemiesForLevel(int level) {
        Integer maxEnemies = maxEnemiesPerLevel.get(level);
        if (maxEnemies == null) {
            return Integer.MAX_VALUE;
        }
        return maxEnemies;
    }
    
    private static Map<Integer, Integer> maxEnemiesPerLevel;
    static {
        maxEnemiesPerLevel = new HashMap<Integer, Integer>();
        maxEnemiesPerLevel.put(1, 10);
        maxEnemiesPerLevel.put(2, 15);
        maxEnemiesPerLevel.put(3, 20);
        maxEnemiesPerLevel.put(4, 35);
        maxEnemiesPerLevel.put(5, 50);
    }
    
    public static double getParLevelTime(int level) {
        Double timeSec = parTimePerLevel.get(level);
        if (timeSec == null) {
            return 1;
        }
        return timeSec;
    }
    
    private static Map<Integer, Double> parTimePerLevel;
    static {
        parTimePerLevel = new HashMap<Integer, Double>();
        parTimePerLevel.put(1, 35d);
        parTimePerLevel.put(2, parTimePerLevel.get(1) + 15d);
        parTimePerLevel.put(3, parTimePerLevel.get(2) + 25d);
        parTimePerLevel.put(4, parTimePerLevel.get(3) + 35d);
        parTimePerLevel.put(5, parTimePerLevel.get(4) + 50d);
        parTimePerLevel.put(6, parTimePerLevel.get(5) + 75d);
    }
    
    public static Double getExperienceRequirementForLevel(int level) {
        Double xpRequired = experienceLevels.get(level);
        if (xpRequired == null) {
            return null; // All levels completed.
        }
        return xpRequired;
    }

    // Experience points required for a player to move up to each level
    private static java.util.Map<Integer, Double> experienceLevels;
    static {
        experienceLevels = new HashMap<Integer, Double>();
        experienceLevels.put(1, 0d);
        experienceLevels.put(2, experienceLevels.get(1) + 4 * new EnemyUnitBanshee().getExperienceValueToKiller());
        experienceLevels.put(3, experienceLevels.get(2) + new EnemyUnitBarbarian().getExperienceValueToKiller());
        experienceLevels.put(4, experienceLevels.get(3) + 500d);
        experienceLevels.put(5, experienceLevels.get(4) + 1000d);
    }
}
