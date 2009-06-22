/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.KeyPress;

import crimsonportal.googlecode.com.Observer.*;
import java.awt.event.KeyEvent;

/**
 *
 * @author dagwud
 */
public interface KeyPressObserver extends Observer<KeyEvent>
{
    public void update(KeyEvent event);
}
