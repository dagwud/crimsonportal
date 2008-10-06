/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectModel.crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public abstract class GameObject 
{
    public GameObject(Location location)
    {
        this.location = location;
    }
    
    public Location getLocation()
    {
        return location;
    }
    
    protected void setLocation(Location location)
    {
        this.location = location;
    }
    
    private Location location;
}
