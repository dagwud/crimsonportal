/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player;

import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.Unit;
import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public class ShootEvent extends EventObject
{
    public ShootEvent(boolean isShootStart, Unit shooter, Bullet bullet)
    {
        super(shooter);
        this.shootEventType = isShootStart;
        this.bullet = bullet;
    }
    
    public Bullet getBullet()
    {
        return bullet;
    }
    
    public boolean isStartEvent()
    {
        return (shootEventType == SHOOTTYPE_START);
    }
    
    public boolean isStopEvent()
    {
        return (shootEventType == SHOOTTYPE_STOP);
    }
    
    private Bullet bullet;
    private boolean shootEventType;
    private static boolean SHOOTTYPE_START = true,
                           SHOOTTYPE_STOP = false;
}
