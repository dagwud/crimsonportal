/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class Pickup extends GameObject
{   
    public Pickup(Location location, GameTime expirationTime)
    {
        super(20.0, location);
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
    
    public abstract void applyTo(GameTime gameTime, Unit unit);
    public abstract void unapplyTo(Unit unit);
    
    protected GameTime expirationTime;
}
