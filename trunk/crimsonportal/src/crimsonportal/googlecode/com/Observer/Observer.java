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
public interface Observer<EventType extends EventObject>
{
    public void update(EventType event);
}
