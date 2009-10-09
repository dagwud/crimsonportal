/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.Strategy;

/**
 *
 * @author jdevenish
 */
public class StrategyLemming extends Strategy {
    public StrategyLemming(GameObject gameObject) {
        super(gameObject);
    }
    
    public StrategyLemming(Location location) {
        super(location);
    }
    
    public StrategyLemming(GameObject gameObject, EnemyUnitLemmingLeader leader) {
        super(gameObject);
        this.leader = leader;
    }
    
    @Override
    public GameObject getTarget() {
        // If the leader is alive, follow the leader:
        if (leader == null) {
            // Leader is dead and has been removed from game; player is the new target:
            return super.getTarget();
        }
        
        if (leader.getHealth() <= 0) {
            // Leader is dead but has not yet been removed from game; player is the new target:
            return super.getTarget();
        }
        
        // Leader is still alive; follow the leader:
        return leader;
    }
    
    private EnemyUnitLemmingLeader leader;
}
