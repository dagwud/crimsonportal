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
public interface PlayerMoveObserver extends Observer<PlayerMoveEvent>
{
    public void update(PlayerMoveEvent event);
}
