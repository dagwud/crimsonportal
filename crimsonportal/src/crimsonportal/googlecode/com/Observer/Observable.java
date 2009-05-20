/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer;

import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public interface Observable<EventType extends EventObject>
{
    public void notifyObservers(EventType event);
    
    public boolean addObserver(Observer<EventType> observer);
    
    public boolean removeObserver(Observer<EventType> observer);
    
    public void removeAllObservers();
    
    public int countObservers();
}
