/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class WeaponPickup extends Pickup
{
    public WeaponPickup(Weapon weapon, GameTime expirationTime)
    {
        super(expirationTime);
        this.weapon = weapon;
    }
    
    public Weapon getWeapon()
    {
        return weapon;
    }
    
    protected void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
    
    public boolean isExpired()
    {
        throw new RuntimeException("Method not implemented: WeaponPickup.isExpired()");
    }
    
    @Override
    public WeaponPickup clone()
    {
        WeaponPickup w = new WeaponPickup(weapon, getExpirationTime());
        return w;
    }
    
    private Weapon weapon;
}
