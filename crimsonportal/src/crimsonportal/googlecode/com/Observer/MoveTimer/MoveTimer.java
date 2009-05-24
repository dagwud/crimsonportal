/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.MoveTimer;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import crimsonportal.googlecode.com.Observer.KeyPress.KeyPressObserver;
import crimsonportal.googlecode.com.Observer.Player.Move.MoveTimerEvent;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveObservable;
import java.awt.event.KeyEvent;
import java.util.Timer;

/**
 *
 * @author dagwud
 */
public class MoveTimer extends Timer implements MoveTimerObservable, KeyPressObserver
{
    public MoveTimer(PlayerUnit playerToMove)
    {
        super();
        Debug.logMethod("Creating move timer for player " + playerToMove);
        timerObservers = new ObserverGroup<MoveTimerEvent>();
        timerTask = new MoveTimerTask(playerToMove);
        this.scheduleAtFixedRate(timerTask, 100, 100);
    }

    public void setMovementX(int moveAmountX)
    {
        timerTask.moveAmountX = moveAmountX;
    }

    public void setMovementY(int moveAmountY)
    {
        timerTask.moveAmountY = moveAmountY;
    }
    
    public double getMovementX()
    {
        return timerTask.moveAmountX;
    }
    
    public double getMovementY()
    {
        return timerTask.moveAmountY;
    }
    
    public void notifyObservers(MoveTimerEvent event)
    {
        if (timerObservers.countObservers() > 0)
        {
            Debug.logMethod("MoveTimer " + this + " is notifying observers");
            timerObservers.notifyObservers(event);
        }
    }

    public boolean addObserver(Observer<MoveTimerEvent> observer)
    {
        return timerObservers.addObserver(observer);
    }

    public boolean removeObserver(Observer<MoveTimerEvent> observer)
    {
        return timerObservers.removeObserver(observer);
    }

    public void removeAllObservers()
    {
        timerObservers.removeAllObservers();
    }
    
    public int countObservers()
    {
        return timerObservers.countObservers();
    }

    public void update(KeyEvent event)
    {
        Debug.logMethod("MoveTimer has received a notification");
        if (timerObservers.countObservers() > 0)
        {
            Debug.logMethod("MoveTimer is notifying observers");
            MoveTimerEvent e = new MoveTimerEvent(this);
            timerObservers.notifyObservers(e);
        }
    }

    private ObserverGroup<MoveTimerEvent> timerObservers;
    private MoveTimerTask timerTask;
}
