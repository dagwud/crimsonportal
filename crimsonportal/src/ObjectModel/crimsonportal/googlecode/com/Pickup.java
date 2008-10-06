/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectModel.crimsonportal.googlecode.com;

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
    
    private GameTime expirationTime;
}
