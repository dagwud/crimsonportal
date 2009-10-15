/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.MenuExit;

import crimsonportal.googlecode.com.Observer.*;
/**
 *
 * @author dagwud
 */
public interface MenuExitObserver extends Observer<MenuListenerEvent> {
    public void update(MenuListenerEvent event);
}
