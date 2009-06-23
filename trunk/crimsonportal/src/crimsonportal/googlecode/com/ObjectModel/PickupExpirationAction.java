/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;

/**
 *
 * @author dagwud
 */
public class PickupExpirationAction extends GameTimerAction {
    public PickupExpirationAction(Pickup pickup, Unit unitHoldingPickup) {
        this.pickup = pickup;
        this.unitHoldingPickup = unitHoldingPickup;
    }
    
    @Override
    public void trigger()
    {
        Debug.logEvent("Pickup expired: Pickup " + this);
        pickup.unapplyTo(unitHoldingPickup);
    }
    
    public String toString() {
        return "PickupExpirationAction[" + pickup + "," + unitHoldingPickup + "]";
    }
    
    protected Pickup pickup;
    protected Unit unitHoldingPickup;
}
