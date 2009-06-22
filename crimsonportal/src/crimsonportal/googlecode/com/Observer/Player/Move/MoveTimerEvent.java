/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player.Move;

import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public class MoveTimerEvent extends EventObject
{
    public MoveTimerEvent(MoveTimer object)
    {
        super(object);
    }
}
