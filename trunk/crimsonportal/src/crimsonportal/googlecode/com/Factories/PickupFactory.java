/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Factories;

import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.GameTime;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.Pickup;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupGrow;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupHealth;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupSpeed;
import java.util.Random;

/**
 *
 * @author dagwud
 */
public class PickupFactory {
 public static enum pickupType {
        PICKUP_SPEEDBOOST,
        PICKUP_SHRINK,
        PICKUP_HEALTH
    }
    
    protected static Pickup createPickup(Location location, pickupType pickupType, 
            GameTime gameTime) {
        switch (pickupType) {
            case PICKUP_SPEEDBOOST:
                return new PickupSpeed(location, gameTime, 2.0);
            case PICKUP_SHRINK:
                return new PickupGrow(location, gameTime, 0.5);
            case PICKUP_HEALTH:
                return new PickupHealth(location, gameTime, 50);
            default:
                throw new UnsupportedOperationException("PickupType " + pickupType + " not found");
        }
    }
    
    public static Pickup createRandomPickup(Location location, GameTime gameTime) {
        int numPickupTypes = pickupType.values().length;
        pickupType type = pickupType.values()[new Random().nextInt(numPickupTypes)];
        return createPickup(location, type, gameTime);
    }
}
