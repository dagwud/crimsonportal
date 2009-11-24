/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.GameSettings;

import crimsonportal.googlecode.com.Factories.EnemyUnitFactory;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitBarbarian;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitCritter;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitFletcher;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitLeech;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitLemmingLeader;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitScuttler;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitSuperCritter;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitZombie;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dagwud
 */
public class LevelSettings {
    public static int getMinEnemiesForLevel(int level) {
        Integer minEnemies = LevelSetting.getSettings(level - 1).maxEnemies;
        if (minEnemies == null) {
            return 1;
        }
        return minEnemies;
    }
    
    public static int getMaxEnemiesForLevel(int level) {
        Integer maxEnemies = LevelSetting.getSettings(level).maxEnemies;
        if (maxEnemies == null) {
            return Integer.MAX_VALUE;
        }
        return maxEnemies;
    }
    
    public static double getParLevelTime(int level) {
        Double timeSec = LevelSetting.getSettings(level).experienceRequired;
        if (timeSec == null) {
            return 1;
        }
        return timeSec;
    }
    
    public static Double getExperienceRequirementForLevel(int level) {
        Double xpRequired = LevelSetting.getSettings(level).experienceRequired;
        if (xpRequired == null) {
            return null; // All levels completed.
        }
        return xpRequired;
    }
}

class LevelSetting {
    private LevelSetting(int maxEnemies, double experienceRequired, 
            double parTimeSec) {
        this.experienceRequired = experienceRequired;
        this.maxEnemies = maxEnemies;
        this.parTimeSec = parTimeSec;
    }

    public static void createSettings(int levelNumber, int maxEnemies, 
            int numKillsRequired, double parTimeSec) {
        if (levelSettings == null) {
            levelSettings = new HashMap<Integer, LevelSetting>();
        }
        
        levelSettings.put(levelNumber, create(maxEnemies,
            levelSettings.get(levelNumber - 1).experienceRequired + (numKillsRequired * getExperienceForOneOfEachEnemy(levelNumber)),
            levelSettings.get(levelNumber - 1).parTimeSec + parTimeSec));
    }
    
    private static LevelSetting create(int maxEnemies, double experienceRequired,
            double parTimeSec) {
        return new LevelSetting(maxEnemies, experienceRequired, parTimeSec);
    }
    
    private static double getExperienceForOneOfEachEnemy(int curLevel) {
        List<EnemyUnit> enemyTemplates = new LinkedList<EnemyUnit>();
        enemyTemplates.add(new EnemyUnitBarbarian());
        enemyTemplates.add(new EnemyUnitCritter());
        enemyTemplates.add(new EnemyUnitFletcher());
        enemyTemplates.add(new EnemyUnitLeech());
        enemyTemplates.add(new EnemyUnitLemmingLeader());
        enemyTemplates.add(new EnemyUnitScuttler());
        enemyTemplates.add(new EnemyUnitSuperCritter());
        enemyTemplates.add(new EnemyUnitZombie());
        
        Iterator<EnemyUnit> it = enemyTemplates.iterator();
        double oneOfEach = 1;
        while (it.hasNext()) {
            EnemyUnit template = it.next();
            int levelReq = EnemyUnitFactory.getEnemyLevelRequirement(template.getEnemyTypeEnum());
            if (levelReq == curLevel) oneOfEach += template.getExperienceValueToKiller();
        }
        return oneOfEach;
    }
    
    public static LevelSetting getSettings(int levelNumber) {
        if (levelSettings == null) {
            initSettings();
        }
        
        return levelSettings.get(levelNumber);
    }
    
    private static void initSettings() {
        levelSettings = new HashMap<Integer, LevelSetting>();
        levelSettings.put(0, new LevelSetting(0, 0, 1));
        LevelSetting.createSettings(1, 10, 0, 35d);
        LevelSetting.createSettings(2, 15, 1, 15d);
        LevelSetting.createSettings(3, 20, 3, 15d);
        LevelSetting.createSettings(4, 30, 4, 35d);
        LevelSetting.createSettings(5, 35, 4, 15d);
        LevelSetting.createSettings(6, 45, 6, 30d);
        LevelSetting.createSettings(7, 60, 8, 20d);
        LevelSetting.createSettings(8, 70, 10, 25d);
        LevelSetting.createSettings(9, 85, 15, 25d);
    }

    int maxEnemies;
    double experienceRequired;
    double parTimeSec;
    private static java.util.Map<Integer, LevelSetting> levelSettings;
}
