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
        this.previousRunTime = numSeconds * 1000;
    }
    
    public GameTime(GameTime gameTime, int numSecondsToAdd) {
        this(gameTime);
        this.previousRunTime = 0;
        this.lastStartTime += (numSecondsToAdd * 1000);
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
    
    public int getNumSeconds() {
        return (int)(Math.floor(getNumMilliseconds() / 1000));
    }
    
    protected void resetTime()
    {
        previousRunTime = 0;
        lastStartTime = System.currentTimeMillis();
    }
    
    @Override
    public GameTime clone()
    {
        GameTime g = new GameTime((int)Math.floor(previousRunTime / 1000.0));
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
        if (thisTime == gTime)
        {
            return 0;
        }
        
        return (this.isAfter(g) ? 1 : -1);
    }
    
    public boolean isAfter(GameTime rhs) {
        long rhsTime = rhs.getNumMilliseconds();
        return (getNumMilliseconds() > rhsTime);
    }
    
    @Override
    public String toString() {
        return "GameTime[" + getNumSeconds() + "s" + (isPaused ? " Paused" : "") + "]";
    }
    
    private long lastStartTime;
    private long previousRunTime;
    private boolean isPaused = false;
}
