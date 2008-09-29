/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class PlayerUnit extends Unit
{
    public PlayerUnit(Location location)
    {
        super(location, new Strategy(new Location(0, 0)));
    }
    
    public PlayerUnit(Location location, Weapon weapon)
    {
        super(location, new Strategy(new Location(0, 0)));
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
    public PlayerUnit clone()
    {
        PlayerUnit p = new PlayerUnit(getLocation(), weapon);
        return p;
    }
    
    private Weapon weapon;
}
