package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponLaser;
import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPistol;

/**
 *
 * @author jdevenish
 */
public class PickupWeaponLaser extends WeaponPickup {

    public PickupWeaponLaser(Location location, GameTime expirationTime) {
        super(location, expirationTime);
    }
    
    @Override
    public Weapon getWeapon() {
        return new WeaponLaser();
    }
    
    @Override
    public String getSpriteFilename() {
        return "pickup_laser.gif";
    }
    
}
