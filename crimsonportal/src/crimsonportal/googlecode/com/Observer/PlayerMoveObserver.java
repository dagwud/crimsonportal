/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer;

/**
 *
 * @author dagwud
 */
public abstract class PlayerMoveObserver extends Observer
{
    public void update(Observable o, PlayerMoveEvent event)
    {
        update(o, event);
    }
    
    public final void update(Observable o, Event event)
    {
        update(o, (PlayerMoveEvent)event);
    }
}
