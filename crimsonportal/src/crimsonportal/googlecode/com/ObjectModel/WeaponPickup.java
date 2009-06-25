/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class WeaponPickup extends PickupSingleUse
{
    public WeaponPickup(Location location, GameTime expirationTime)
    {
        super(location, expirationTime);
    }
    
    @Override
    public final void applyTo(GameTime elapseGameTime, Unit unitWithWeapon)
    {
        // Give the unit the weapon:
        UnitWithWeapon unit;
        try {
            unit = (UnitWithWeapon)unitWithWeapon;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Attempt to apply weapon to unit which is not of type UnitWithWeapon: " + unitWithWeapon);
        }
        unit.setWeapon( getWeapon() );
    }
    
    public abstract Weapon getWeapon();
}
