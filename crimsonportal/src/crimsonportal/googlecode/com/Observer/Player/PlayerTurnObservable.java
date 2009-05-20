/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player;

import crimsonportal.googlecode.com.Observer.*;
import crimsonportal.googlecode.com.ObjectModel.PlayerTurnEvent;

/**
 *
 * @author dagwud
 */
public interface PlayerTurnObservable
{
    public void notifyObservers(PlayerTurnEvent event);
    
    public boolean addObserver(Observer<PlayerTurnEvent> observer);
    
    public boolean removeObserver(Observer<PlayerTurnEvent> observer);
    
    public void removeAllObservers();
    
    public int countObservers();
}
