/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitFletcher;

/**
 *
 * @author jdevenish
 */
public class MovementHandlerAttackAndRetreat extends MovementHandlerStraightLine {
    public MovementHandlerAttackAndRetreat(double moveSpeed) {
        super(moveSpeed);
    }
    
    @Override
    public void move(Unit moveObject) {
        EnemyUnitFletcher f = (EnemyUnitFletcher)moveObject;
        if (f.isRetreating()) {
            // Retreating:
            f.incrementRetreatProgress();
        }
        super.move(f, f.isRetreating());
    }
}
