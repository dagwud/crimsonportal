/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.MenuExit;

import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public class MenuListenerEvent extends EventObject {
    public MenuListenerEvent(Type type) {
        super(type);
    }
    
    public Type getEventType() {
        return (Type)source;
    }

    public enum Type
    {
        MENU_OPEN, MENU_CLOSED
    };
}
