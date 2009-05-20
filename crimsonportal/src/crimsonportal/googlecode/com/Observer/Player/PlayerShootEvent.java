/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player;

import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public class PlayerShootEvent extends EventObject
{
    public PlayerShootEvent(PlayerUnit shooter, Bullet bullet)
    {
        super(shooter);
        this.bullet = bullet;
    }
    
    public Bullet getBullet()
    {
        return bullet;
    }
    
    private Bullet bullet;
}
