/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Controller;

import crimsonportal.googlecode.com.Observer.Player.Move.MoveTimer;
import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.PlayerTurnEvent;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import crimsonportal.googlecode.com.Observer.Observable;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author dagwud
 */
public class TurnListener implements MouseMotionListener, Observable<PlayerTurnEvent>
{
    public TurnListener(PlayerUnit controlledPlayer)
    {
        Debug.logMethod("Initialising TurnListener for player " + controlledPlayer);
        observers = new ObserverGroup<PlayerTurnEvent>();
        this.controlledPlayer = controlledPlayer;
        observers.addObserver(controlledPlayer);
    }
    
    public void mouseMoved(MouseEvent e) 
    {
        int x = e.getX();
        int y = e.getY();
        PlayerTurnEvent ev = new PlayerTurnEvent(controlledPlayer, new Location(x, y));
        observers.notifyObservers(ev);
    }
    
    public void mouseDragged(MouseEvent e) 
    {
        mouseMoved(e);
    }
    
    
    public void keyTyped(KeyEvent e) {}
    
    public void notifyObservers(PlayerTurnEvent event)
    {
        if (observers.countObservers() > 0)
        {
            Debug.logMethod("TurnListener " + this + " is notifying observers");
            observers.notifyObservers(event);
        }
    }

    public void removeAllObservers()
    {
        observers.removeAllObservers();
    }
    
    public int countObservers()
    {
        return observers.countObservers();
    }
    
    public boolean addObserver(Observer<PlayerTurnEvent> observer)
    {
        return observers.addObserver(observer);
    }

    public boolean removeObserver(Observer<PlayerTurnEvent> observer)
    {
        return observers.removeObserver(observer);
    }
    
    private PlayerUnit controlledPlayer;
    private ObserverGroup<PlayerTurnEvent> observers;
    private MoveTimer moveTimer;
}
