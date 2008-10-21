/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class Pickup 
{
    public Pickup(GameTime expirationTime)
    {
        this.expirationTime = expirationTime;
    }
    
    public GameTime getExpirationTime()
    {
        return expirationTime;
    }
    
    protected void setExpirationTime(GameTime expirationTime)
    {
        this.expirationTime = expirationTime;
    }
    
    public void setPickupLocation(Location loc) 
    {
        pickupLocation.setX( loc.getX() );
        pickupLocation.setY( loc.getY() );
    }
    
    public void setPickupLocation(int locX, int locY) 
    {
        pickupLocation.setX(locX);
        pickupLocation.setY(locY);
    }
    
    public Location getPickupLocation() 
    {
        return pickupLocation;
    }
    
    private Location pickupLocation;
    private GameTime expirationTime;
}
