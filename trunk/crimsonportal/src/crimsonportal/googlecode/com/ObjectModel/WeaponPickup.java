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
    public WeaponPickup(double size, Location location, GameTime expirationTime, Weapon weapon)
    {
        super(size, location, expirationTime);
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
    
    @Override
    public WeaponPickup clone()
    {
        return new WeaponPickup(size, location, expirationTime, weapon);
    }
    
    public String getSpriteFilename()
    {
        return "pickup.gif";
    }
    private Weapon weapon;
}
