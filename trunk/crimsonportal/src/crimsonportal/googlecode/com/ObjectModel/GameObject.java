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
        this.location = location.clone();
    }
    
    public Location getCentreOfObject()
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
    
    protected void setCentreOfObject(Location location)
    {
        this.location = location;
    }
    
    public boolean testOverlapsWith(GameObject obj)
    {
        double xdiff = obj.getCentreOfObject().getX() - getCentreOfObject().getX();
        double ydiff = obj.getCentreOfObject().getY() - getCentreOfObject().getY();
        double overlapMaxDistance = (obj.getSize() / 2) + (getSize() / 2);
        
        double distBetweenObjs = Math.sqrt((xdiff * xdiff) + (ydiff * ydiff));
        return (distBetweenObjs <= overlapMaxDistance);
    }
    
    public abstract String getSpriteFilename();
    
    protected Location location;
    protected double size;
    protected double rotation;
}
