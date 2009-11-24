/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.Weapons;

import crimsonportal.googlecode.com.ObjectModel.*;

/**
 *
 * @author dagwud
 */

public class BulletPistol extends Bullet {
    public BulletPistol(UnitWithWeapon shooter, GameObject target, GameTime spawnTime) {
        super(PRESET_SIZE_SMALL, 
                shooter, 
                shooter.getCentreOfObject(), 
                new Strategy(target), 
                PRESET_DAMAGE_WEAK,
                spawnTime);
    }

    @Override
    public double getDefaultMoveSpeed() {
        return PRESET_SPEED_SLOW;
    }
    
    @Override
    public int getDefaultAttackSpeed() {
        return PRESET_ATTACKSPEED_SLOW;
    }

    @Override
    public Bullet clone() {
        return new BulletPistol(shooter, getStrategy().getTarget(), spawnTime);
    }

    @Override
    public String getSpriteFilename() {
        return "bullet_pistol.gif";
    }
}