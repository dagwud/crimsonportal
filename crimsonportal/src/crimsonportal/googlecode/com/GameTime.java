/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public class GameTime 
{
    public GameTime()
    {
        numSeconds = 0;
    }
    
    public GameTime(int numSeconds)
    {
        this.numSeconds = numSeconds;
    }
    
    public int getNumSeconds()
    {
        return numSeconds;
    }
    
    public void setNumSeconds(int numSeconds)
    {
        this.numSeconds = numSeconds;
    }
    
    public void resetTime()
    {
        numSeconds = 0;
    }
    
    @Override
    public GameTime clone()
    {
        GameTime g = new GameTime(numSeconds);
        return g;
    }

    protected int numSeconds;
}
