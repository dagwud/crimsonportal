/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.GameState;

import crimsonportal.googlecode.com.Observer.*;

/**
 *
 * @author dagwud
 */
public interface GameStateChangedObserver extends Observer<GameStateChangedEvent>
{
    public void update(GameStateChangedEvent event);
}
