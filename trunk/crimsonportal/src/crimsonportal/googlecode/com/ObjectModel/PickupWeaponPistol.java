package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.ObjectModel.Weapons.WeaponPistol;

/**
 *
 * @author jdevenish
 */
public class PickupWeaponPistol extends WeaponPickup {

    public PickupWeaponPistol(Location location, GameTime expirationTime) {
        super(location, expirationTime);
    }
    
    @Override
    public Weapon getWeapon() {
        return new WeaponPistol();
    }
    
    @Override
    public String getSpriteFilename() {
        return "pickup_pistol.gif";
    }
    
}
