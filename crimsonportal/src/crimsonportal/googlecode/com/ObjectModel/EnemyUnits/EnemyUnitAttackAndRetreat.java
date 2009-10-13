/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.EnemyUnits;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.MovementHandlerAttackAndRetreat;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;

/**
 *
 * @author jdevenish
 */
public abstract class EnemyUnitAttackAndRetreat extends EnemyUnit {
    protected EnemyUnitAttackAndRetreat(double size, double startingHealth, int attackDamage, double attackSpeed, int moveSpeed, Location location, GameObject target, GameState gameState, int retreatDistance)
    {
        super(size, startingHealth, attackDamage, attackSpeed, moveSpeed, location, target, gameState);
        this.retreatDistance = retreatDistance;
    }
    
    @Override
    public void attack(PlayerUnit player) {
        try {
            MovementHandlerAttackAndRetreat handler = (MovementHandlerAttackAndRetreat)this.getMovementHandler();
        }
        catch (ClassCastException e) {
            throw new RuntimeException("Subclass of EnemyUnitAttackAndRetreat must return object of type MovementHandlerAttackAndRetreat from getMovementHandler() method");
        }
        
        if (isRetreating()) {
            // Retreating; don't allow an attack
            return;
        } else {
            // Start retreat (after this attack):
            startRetreat(retreatDistance);
        }
        super.attack(player);
    }
    
    public boolean isRetreating() {
        return (retreatProgress >= 0);
    }
    
    public void incrementRetreatProgress() {
        retreatProgress--;
        if (retreatProgress < -1) retreatProgress = -1;
    }
    
    public void startRetreat(int retreatDistance) {
        if (retreatDistance <= 0) {
            throw new IllegalArgumentException("Retreat distance must be > 0");
        }
        retreatProgress = retreatDistance;
    }
    
    protected int retreatProgress = 0;
    protected int retreatDistance;
}
