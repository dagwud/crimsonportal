/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Factories;

import crimsonportal.googlecode.com.ObjectModel.GameTime;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.Pickup;
import crimsonportal.googlecode.com.ObjectModel.PickupWeaponPistol;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupArmour;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupGrow;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupHealth;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupNuke;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupSpeed;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupWeaponUzi;
import crimsonportal.googlecode.com.ObjectModel.WeaponPickup;
import java.util.Random;

/**
 *
 * @author dagwud
 */
public class PickupFactory {
    public static enum pickupType {
        PICKUP_SPEEDBOOST,
        PICKUP_SHRINK,
        PICKUP_HEALTH,
        PICKUP_ARMOUR,
        PICKUP_NUKE,
        PICKUP_WEAPON
    }
 
    public static enum weaponType {
        WEAPON_PISTOL,
        WEAPON_UZI
    }
    
    protected static Pickup createPickup(Location location, pickupType pickupType, 
            GameTime expirationTime) {
        switch (pickupType) {
            case PICKUP_SPEEDBOOST:
                return new PickupSpeed(location, expirationTime, 2.0);
            case PICKUP_SHRINK:
                return new PickupGrow(location, expirationTime, 0.5);
            case PICKUP_HEALTH:
                return new PickupHealth(location, expirationTime, 50);
            case PICKUP_ARMOUR:
                return new PickupArmour(location, expirationTime);
            case PICKUP_NUKE:
                return new PickupNuke(location, expirationTime);
            default:
                throw new UnsupportedOperationException("PickupType " + pickupType + " not found");
        }
    }
    
    protected static Pickup createWeaponPickup(Location location, weaponType weaponType,
            GameTime expirationTime) {
        switch (weaponType) {
            case WEAPON_PISTOL:
                return new PickupWeaponPistol(location, expirationTime);
            case WEAPON_UZI:
                return new PickupWeaponUzi(location, expirationTime);
            default:
                throw new UnsupportedOperationException("WeaponType " + weaponType + " not found");
        }
    }
    
    public static Pickup createRandomPickup(Location location, GameTime gameTime) {
        int numPickupTypes = pickupType.values().length;
        pickupType type = pickupType.values()[new Random().nextInt(numPickupTypes)];
        if (type != pickupType.PICKUP_WEAPON) {
            return createPickup(location, type, gameTime);
        } else {
            int numWeaponTypes = weaponType.values().length;
            weaponType weapon = weaponType.values()[new Random().nextInt(numWeaponTypes)];
            return createWeaponPickup(location, weapon, gameTime);
        }
    }
}
