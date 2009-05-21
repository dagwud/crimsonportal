/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.GameSettings;

import java.util.HashMap;
import java.util.Map;

public class EnemyStats
{
    protected EnemyStats(int enemySpeed, double enemySize)
    {
        this.enemySpeed = enemySpeed;
        this.enemySize = enemySize;
    }
    
    public static EnemyStats getEnemyStats(int EnemyType)
    {
        return statsMap.get(EnemyType);
    }
    
    public int getMoveSpeed()
    {
        return enemySpeed;
    }
    
    public double getSize()
    {
        return enemySize;
    }
    
    // Properties of an EnemyStats instance:
    protected int enemySpeed;
    protected double enemySize;

    // Keys for use when looking up enemy stats:
    public static final int ENEMY_TINY = 1;
    public static final int ENEMY_SMALL = 2;
    public static final int ENEMY_MEDIUM = 3;
    public static final int ENEMY_LARGE = 4;
    
    // Initialise the enemy stats map with details
    // of each enemy type:
    private static Map<Integer, EnemyStats> statsMap;
    static
    {
        statsMap = new HashMap<Integer, EnemyStats>();
        statsMap.put(ENEMY_TINY,  new EnemyStats(1, 10d));
        statsMap.put(ENEMY_SMALL, new EnemyStats(2, 30d));
        statsMap.put(ENEMY_MEDIUM, new EnemyStats(3, 40d));
        statsMap.put(ENEMY_LARGE,  new EnemyStats(2, 50d));
    }
}