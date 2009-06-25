/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.Pickups;

import crimsonportal.googlecode.com.ObjectModel.GameTime;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.Weapon;
import crimsonportal.googlecode.com.ObjectModel.WeaponPickup;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponUzi;

/**
 *
 * @author dagwud
 */
public class PickupWeaponUzi extends WeaponPickup {
    public PickupWeaponUzi(Location location, GameTime expirationTime) {
        super(location, expirationTime);
    }
    
    @Override
    public Weapon getWeapon() {
        return new WeaponUzi();
    }
    
    @Override
    public String getSpriteFilename() {
        return "pickup_uzi.gif";
    }
}
