/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.LocationObject;
import crimsonportal.googlecode.com.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author dagwud
 */
public class EnemyUnitLemmingLeader extends EnemyUnit {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    protected static final int ATTACK_DAMAGE = PRESET_ATTACKDAMAGE_HURT;
    protected static final double ATTACK_SPEED = PRESET_ATTACKSPEED_MODERATE;
    protected static int MOVE_SPEED = PRESET_MOVESPEED_WALK;
    protected static double DEFAULT_HEALTH = 10;
    protected static final MovementType MOVEMENT_TYPE = EnemyUnit.MovementType.MOVEMENT_STRAIGHTLINE;
    private static final String SPRITE_FILENAME = "enemy_lemmingleader.gif";
    
    public EnemyUnitLemmingLeader(Location location, GameObject target, GameState gameState)
    {
        super(SIZE, DEFAULT_HEALTH, ATTACK_DAMAGE, ATTACK_SPEED, MOVE_SPEED, location, new LocationObject(100,100), gameState);
        
        // Create lemmings to follow this leader:
        final int distanceBetweenLemmingsAndLeader = 30;
        final int numLemmingsToCreate = 4;
        
        final double angleToTargetDegrees = Math.toDegrees(getRotation()) + 90.0;
        final double degreesPerLemming = 360.0 / (numLemmingsToCreate);
        
        double leaderX = location.getX(),
               leaderY = location.getY();
        lemmings = new Vector<EnemyUnitLemming>(numLemmingsToCreate);
        for (int i = 0; i < numLemmingsToCreate; i++) {
            // Determine where to place the current lemming:
            double angleToLemmingDegrees = ((i * degreesPerLemming) + angleToTargetDegrees);
            
            double xdiff = distanceBetweenLemmingsAndLeader * Utils.sin(Math.toRadians(angleToLemmingDegrees));
            double ydiff = distanceBetweenLemmingsAndLeader * Utils.cos(Math.toRadians(angleToLemmingDegrees));
            
            double x = leaderX + xdiff;
            double y = leaderY + ydiff;
            
            Location lemmingLoc = new Location(x, y);
            
            // Create the current lemming and add make it follow the leader:
            EnemyUnitLemming lemming = new EnemyUnitLemming(lemmingLoc, target, gameState, this);
            lemmings.add(lemming);
        }
    }
    
    public EnemyUnit[] getLeaderAndLemmings() {
        EnemyUnit[] ret = new EnemyUnit[lemmings.size() + 1];
        ret[0] = this;
        for (int i = 0; i < lemmings.size(); i++) {
            ret[i + 1] = lemmings.get(i);
        }
        return ret;
    }
    
    public MovementType getMovementType() {
        return MOVEMENT_TYPE;
    }
    
    @Override
    public EnemyUnit clone()
    {
        return new EnemyUnitLemmingLeader(location, strategy.getTarget(), gameState);
    }

    @Override
    public String getSpriteFilename()
    {
        return SPRITE_FILENAME;
    }
    
    protected List<EnemyUnitLemming> lemmings;
}
