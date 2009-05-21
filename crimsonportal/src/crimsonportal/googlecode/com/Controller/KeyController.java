/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Controller;

import crimsonportal.googlecode.com.Observer.MoveTimer.MoveTimer;
import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import crimsonportal.googlecode.com.Observer.KeyPress.KeyPressObservable;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author dagwud
 */
public class KeyController implements KeyListener, KeyPressObservable
{
    public KeyController(PlayerUnit controlledPlayer)
    {
        Debug.logMethod("Initialising KeyController for player " + controlledPlayer);
        observers = new ObserverGroup<KeyEvent>();
        moveTimer = new MoveTimer(controlledPlayer);
        addObserver(moveTimer);
    }
    
    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        
        switch (key)
        {
            case KeyEvent.VK_UP:
                moveTimer.setMovementY(-1);
                break;
                
            case KeyEvent.VK_DOWN:
                moveTimer.setMovementY(1);
                break;
                
            case KeyEvent.VK_LEFT:
                moveTimer.setMovementX(-1);
                break;
            
            case KeyEvent.VK_RIGHT:
                moveTimer.setMovementX(1);
                break;
        }
        notifyObservers(e);
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int key = e.getKeyCode();
        
        switch (key)
        {
            case KeyEvent.VK_UP:
                moveTimer.setMovementY(0);
                break;
                
            case KeyEvent.VK_DOWN:
                moveTimer.setMovementY(0);
                break;
                
            case KeyEvent.VK_LEFT:
                moveTimer.setMovementX(0);
                break;
            
            case KeyEvent.VK_RIGHT:
                moveTimer.setMovementX(0);
                break;
        }
        notifyObservers(e);
    }
    
    public void keyTyped(KeyEvent e) {}
    
    public void notifyObservers(KeyEvent event)
    {
        if (observers.countObservers() > 0)
        {
            Debug.logMethod("KeyController " + this + " is notifying observers");
            observers.notifyObservers(event);
        }
    }

    public boolean addObserver(Observer<KeyEvent> observer)
    {
        return observers.addObserver(observer);
    }

    public boolean removeObserver(Observer<KeyEvent> observer)
    {
        return observers.removeObserver(observer);
    }

    public void removeAllObservers()
    {
        observers.removeAllObservers();
    }
    
    public int countObservers()
    {
        return observers.countObservers();
    }
    
    private ObserverGroup<KeyEvent> observers;
    private MoveTimer moveTimer;
}
