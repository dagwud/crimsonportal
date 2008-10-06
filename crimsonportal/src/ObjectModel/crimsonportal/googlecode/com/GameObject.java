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
    public GameObject(int size, Location location)
    {
        this.size = size;
        this.location = location;
    }
    
    public Location getLocation()
    {
        return location;
    }
    
    public int getSize()
    {
        return size;
    }
    
    protected void setLocation(Location location)
    {
        this.location = location;
    }
    
    private Location location;
    private int size;
}
