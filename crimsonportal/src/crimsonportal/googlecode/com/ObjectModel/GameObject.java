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
    
    public double getRotation()
    {
        return rotation;
    }
    
    protected void setRotation(double rotation)
    {
        this.rotation = rotation;
    }
    
    protected void setLocation(Location location)
    {
        this.location = location;
    }
    
    public abstract String getSpriteFilename();
    
    protected Location location;
    protected double size;
    protected double rotation;
}
