/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

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
        pickup.unapplyTo(unitHoldingPickup);
    }
    
    public String toString() {
        return "PickupExpirationAction[" + pickup + "," + unitHoldingPickup + "]";
    }
    
    protected Pickup pickup;
    protected Unit unitHoldingPickup;
}
