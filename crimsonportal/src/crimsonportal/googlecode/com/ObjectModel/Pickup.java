/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import java.util.Random;

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
    
    public void setPickupLocation(double locX, double locY) 
    {
        pickupLocation.setX(locX);
        pickupLocation.setY(locY);
    }
    
    public void generatePickupLocation (Map map)
    {
        //TODO : Fix coordinates . . . 
        
        Random randomGenerator = new Random();
        
        double randomX = randomGenerator.nextInt(map.height - 10);
        double randomY = randomGenerator.nextInt(map.width - 10);
        
        setPickupLocation(randomX, randomY);
    }
    
    public Location getPickupLocation() 
    {
        return pickupLocation;
    }
    
    private Location pickupLocation;
    private GameTime expirationTime;
}
