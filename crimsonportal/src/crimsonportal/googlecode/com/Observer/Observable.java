/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dagwud
 */
public abstract class Observable 
{
    public Observable()
    {
        observers = new LinkedList<Observer>();
    }
    
    public void notifyObservers(Event event)
    {
        Iterator<Observer> i = observers.iterator();
        while (i.hasNext())
        {
            i.next().update(this, event);
        }
    }
    
    public boolean addObserver(Observer observer)
    {
        return observers.add(observer);
    }
    
    public boolean removeObserver(Observer observer)
    {
        return observers.remove(observer);
    }
    
    public void removeAllObservers()
    {
        observers.clear();
    }
    
    private List<Observer> observers;
}
