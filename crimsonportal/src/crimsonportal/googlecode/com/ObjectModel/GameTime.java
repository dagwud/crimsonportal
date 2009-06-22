/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class GameTime implements Comparable<GameTime>
{
    public GameTime(boolean isPaused)
    {
        startTimer();
        if (isPaused)
        {
            pauseTimer();
        }
    }
    
    public GameTime(long numSeconds)
    {
        startTimer();
        this.previousRunTime = numSeconds;
    }
    
    public GameTime(GameTime gameTime)
    {
        this.previousRunTime = gameTime.previousRunTime;
        this.lastStartTime = gameTime.lastStartTime;
        this.isPaused = gameTime.isPaused;
    }
    
    public void startTimer()
    {
        lastStartTime = System.currentTimeMillis();
        isPaused = false;
    }
    
    public void pauseTimer()
    {
        previousRunTime = getNumMilliseconds();
        isPaused = true;
    }
    
    public long getNumMilliseconds()
    {
        if (isPaused)
        {
            return previousRunTime;
        }
        else
        {
            return previousRunTime + System.currentTimeMillis() - lastStartTime;
        }
    }
    
    protected void resetTime()
    {
        previousRunTime = 0;
        lastStartTime = System.currentTimeMillis();
    }
    
    @Override
    public GameTime clone()
    {
        GameTime g = new GameTime(previousRunTime);
        return g;
    }

    public boolean isPaused()
    {
        return isPaused;
    }
    
    public int compareTo(GameTime g)
    {
        long gTime = g.getNumMilliseconds();
        long thisTime = this.getNumMilliseconds();
        if (thisTime < gTime)
        {
            return -1;
        }
        else if (thisTime > gTime)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    private long lastStartTime;
    private long previousRunTime;
    private boolean isPaused = false;
}
