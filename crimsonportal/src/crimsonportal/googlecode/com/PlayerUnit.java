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
    public PlayerUnit()
    {
        
    }
    
    public PlayerUnit(Weapon weapon)
    {
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
    
    public PlayerUnit clone()
    {
        PlayerUnit p = new PlayerUnit(weapon);
        return p;
    }
    
    protected Weapon weapon;
}
