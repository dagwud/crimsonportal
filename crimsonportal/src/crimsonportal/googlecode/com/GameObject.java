/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

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
    
    public void setLocation(Location location)
    {
        this.location = location;
    }
    
    protected Location location;
}
