/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author dagwud
 */
public class GameTimer extends Thread
{
    private GameTimer(GameTime gameTime)
    {
        GameTimer.gameTime = gameTime;
        gameTimerInstance = this;
        gameTimerTasks = new Vector<GameTimerTask>();
    }
    
    @Override
    public void run()
    {
        while (true) {
            getInstance(GameTimer.gameTime).checkTimers();
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {} // Suppress
        }
    }
    
    public static GameTimer getInstance(GameTime gameTime)
    {
        if (gameTimerInstance == null) {
            if (gameTime == null) {
                throw new IllegalArgumentException("getInstance(GameTime) called on Singleton GameTimer, but gameTime was not provided");
            }
            gameTimerInstance = new GameTimer(gameTime);
        }
        return gameTimerInstance;
    }
    
    public static GameTimer getInstance() {
        if (gameTimerInstance == null) {
            throw new RuntimeException("getInstance() called on Singleton GameTimer, but gameTime has not been set");
        }
        return gameTimerInstance;
    }
    
    public void addTimer(GameTime time, GameTimerAction action)
    {
        int i = gameTimerTasks.size() - 1;
        if (gameTimerTasks.size() > 0) {
            while (i >= 0 && gameTimerTasks.get(i).getTriggerTime().isAfter(time)) {
                i--;
            }
        }
        GameTimerTask task = new GameTimerTask(time, action);
        gameTimerTasks.insertElementAt(task, i + 1);
        System.out.println("Added TimerTask " + task);
    }
    
    public void checkTimers()
    {
        System.out.println("Checking timers - gameTime = " + gameTime);
        Iterator<GameTimerTask> it = gameTimerTasks.iterator();
        boolean finished = false;
        while (it.hasNext() && !finished) {
            GameTimerTask t = it.next();
            System.out.println("  Checking " + t);
            if (t.checkTriggered(gameTime)) {
                System.out.println("    Timer has expired");
                it.remove();
            }
            else {
                // This task has not yet triggered, so none of the consecutive
                // tasks will have. Skip the rest:
                finished = true;
            }
        }
        
    }
    
    protected static GameTime gameTime;
    protected Vector<GameTimerTask> gameTimerTasks;
    private static GameTimer gameTimerInstance;
}
