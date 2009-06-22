/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Controller;

import crimsonportal.googlecode.com.Observer.Observable;
import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public abstract class Controller<EventType extends EventObject>  
                                        implements Observable<EventType>
{

}
