/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectModel.crimsonportal.googlecode.com;

import GameSettings.crimsonportal.googlecode.com.ObjectSizes;

/**
 *
 * @author dagwud
 */
public class PlayerUnit extends Unit
{
    public PlayerUnit(Location location)
    {
        super(ObjectSizes.PLAYER_SIZE, location, new Strategy(new Location(0, 0)));
    }
    
    public PlayerUnit(Location location, Weapon weapon)
    {
        super(ObjectSizes.PLAYER_SIZE, location, new Strategy(new Location(0, 0)));
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
