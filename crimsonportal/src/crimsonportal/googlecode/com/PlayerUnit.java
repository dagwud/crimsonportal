/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public class PlayerUnit extends Unit
{
    public PlayerUnit(Location location)
    {
        super(location);
    }
    
    public PlayerUnit(Location location, Weapon weapon)
    {
        super(location);
        this.weapon = weapon;
    }
    
    public Weapon getWeapon()
    {
        return weapon;
    }
    
    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
    
    @Override
    public PlayerUnit clone()
    {
        PlayerUnit p = new PlayerUnit(location, weapon);
        return p;
    }
    
    protected Weapon weapon;
}
