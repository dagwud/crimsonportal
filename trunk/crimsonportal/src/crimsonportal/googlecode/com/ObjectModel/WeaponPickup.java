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
    public WeaponPickup(Location location, GameTime expirationTime, Weapon weapon)
    {
        super(location, expirationTime);
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
    
    public void applyTo(GameTime elapseGameTime, Unit unit)
    {
        
    }
    
    public void unapplyTo(Unit unit)
    {
        
    }
    
    @Override
    public WeaponPickup clone()
    {
        return new WeaponPickup(location, getExpirationTime(), weapon);
    }
    
    public String getSpriteFilename()
    {
        return "pickup.gif";
    }
    private Weapon weapon;
}
