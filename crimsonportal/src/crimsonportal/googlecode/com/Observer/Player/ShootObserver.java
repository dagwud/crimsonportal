/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player;

import crimsonportal.googlecode.com.Observer.*;

/**
 *
 * @author dagwud
 */
public class ShootObserver implements Observer<ShootEvent>, 
        PlayerShootObservable
{
    public ShootObserver()
    {
        observers = new ObserverGroup<ShootEvent>();
    }
    
    public void update(ShootEvent e) 
    {
        notifyObservers(e);
    }
    
    public void notifyObservers(ShootEvent event)
    {
        observers.notifyObservers(event);
    }

    public boolean addObserver(Observer<ShootEvent> observer)
    {
        return observers.addObserver(observer);
    }

    public boolean removeObserver(Observer<ShootEvent> observer)
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
    
    private ObserverGroup<ShootEvent> observers;
}
