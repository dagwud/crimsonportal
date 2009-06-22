/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player.Turn;

import crimsonportal.googlecode.com.Observer.*;
import crimsonportal.googlecode.com.ObjectModel.PlayerTurnEvent;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;

/**
 *
 * @author dagwud
 */
public class PlayerTurnObserver implements Observer<PlayerTurnEvent> {
    
    public PlayerTurnObserver(PlayerUnit playerToTurn)
    {
        this.controlledPlayer = playerToTurn;
    }
    
    public void update(PlayerTurnEvent e) 
    {
        double rotation = e.getRotation();
        controlledPlayer.notifyObservers(e);
    }
    
    protected PlayerUnit controlledPlayer;
}
