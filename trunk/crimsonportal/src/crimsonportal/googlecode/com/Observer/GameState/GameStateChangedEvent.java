/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.GameState;

import crimsonportal.googlecode.com.ObjectModel.GameState;
import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public class GameStateChangedEvent extends EventObject
{
    public GameStateChangedEvent(GameState gameState)
    {
        super(gameState);
    }
}
