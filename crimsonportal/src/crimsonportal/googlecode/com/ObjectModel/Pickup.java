/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.GameSettings.ObjectSizes;
import java.util.Random;

/**
 *
 * @author dagwud
 */
public abstract class Pickup extends GameObject
{   
    public Pickup(double size, Location location, GameTime expirationTime)
    {
        super(size, location);
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
    
    /**
     * This method generates pickupLocation and checks that its within bounds
     * @param Mam map
     * @return void
     */
    public static Location generateLocation(Map map)
    {
        Random randomGenerator = new Random();
        
        double randomX = randomGenerator.nextDouble() * (map.height - ObjectSizes.PICKUP_SIZE);
        double randomY = randomGenerator.nextDouble() * (map.width - ObjectSizes.PICKUP_SIZE);
        
        Location loc = new Location(randomX, randomY);
        return loc;
    }
    
    protected GameTime expirationTime;
}
