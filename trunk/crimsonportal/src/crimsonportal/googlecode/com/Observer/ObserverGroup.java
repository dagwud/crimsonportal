/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer;

import crimsonportal.googlecode.com.Debug;
import java.util.EventObject;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author dagwud
 */
public class ObserverGroup<EventType extends EventObject> 
                                    implements Observable<EventType>
{
    public ObserverGroup()
    {
        observers = new Vector<Observer<EventType>>();
    }
    
    public void removeAllObservers()
    {
        observers.clear();
    }
    
    public boolean addObserver(Observer<EventType> o)
    {
        return observers.add(o);
    }
    
    public boolean removeObserver(Observer<EventType> o)
    {
        return observers.remove(o);
    }
    
    public void notifyObservers(EventType e)
    {
        Iterator<Observer<EventType>> i = observers.iterator();
        while (i.hasNext())
        {
            Observer<EventType> o = i.next();
            Debug.logMethod(" Notifying " + o);
            o.update(e);
        }
    }
    
    public int countObservers()
    {
        return observers.size();
    }
    
    private Vector<Observer<EventType>> observers;
}
