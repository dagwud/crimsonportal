/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.Weapons;

import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameTime;
import crimsonportal.googlecode.com.ObjectModel.Strategy;
import crimsonportal.googlecode.com.ObjectModel.UnitWithWeapon;

/**
 *
 * @author dagwud
 */
public class BulletUzi extends Bullet {
    public BulletUzi(UnitWithWeapon shooter, GameObject target, GameTime spawnTime) {
        super(Bullet.PRESET_SIZE_MEDIUM, 
                shooter, shooter.getCentreOfObject(),
                new Strategy(target), 
                PRESET_DAMAGE_WEAK,
                spawnTime);
    }

    @Override
    public double getDefaultMoveSpeed() {
        return PRESET_SPEED_NORMAL;
    }
    
    @Override
    public int getDefaultAttackSpeed() {
        return PRESET_ATTACKSPEED_FAST;
    }
    
    @Override
    public Bullet clone() {
        return new BulletUzi(shooter, getStrategy().getTarget(), spawnTime.clone());
    }

    @Override
    public String getSpriteFilename() {
        return "bullet_uzi.gif";
    }
}
