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
    
    /**
     * generates the pickup location and assigns the X and Y values
     * 
     * @param locX
     * @param locY
     */
    public void setPickupLocation(double locX, double locY) 
    {
        pickupLocation.setX(locX);
        pickupLocation.setY(locY);
    }
    /**
     * This method generates pickupLocation and checks that its within bounds
     * @param Mam map
     * @return void
     */
    public void generatePickupLocation (Map map)
    {
        Random randomGenerator = new Random();
        
        double randomX = randomGenerator.nextInt(map.height - 10);
        double randomY = randomGenerator.nextInt(map.width - 10);
        
        // make sure that the pickup doesnt go beyond the screen
        if( randomX < 10 ) 
        {
            randomX+=(double)10;
        }
        
        if( randomY < 10 ) 
        {
            randomY+=(double)10;
        }
        
        setPickupLocation(randomX, randomY);
    }
    
    public Location getPickupLocation() 
    {
        return pickupLocation;
    }
    
    private Location pickupLocation;
    private GameTime expirationTime;
}
