/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class GameObject
{
    public GameObject(double size, Location location)
    {
        this.size = size;
        this.location = location;
    }
    
    public Location getLocation()
    {
        return location;
    }
    
    public double getSize()
    {
        return size;
    }
    
    protected void setLocation(Location location)
    {
        this.location = location;
    }
    
    private Location location;
    private double size;
}
