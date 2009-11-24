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

public class BulletLaser extends Bullet {
    public BulletLaser(UnitWithWeapon shooter, GameObject target, GameTime spawnTime) {
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
        return PRESET_ATTACKSPEED_FAST;
    }

    @Override
    public Bullet clone() {
        return new BulletPistol(shooter, getStrategy().getTarget(), spawnTime);
    }
    
    @Override
    public double getRadius() {
        double r = super.getRadius();
        long timeSinceSpawn = getTimeSinceSpawnMS();
        long maxTimeSinceSpawnMS = 1000;
        long sizePerc = Math.min(timeSinceSpawn / maxTimeSinceSpawnMS, 100);
        r = r * (1.0 + sizePerc);
        return r;
    }

    @Override
    public String getSpriteFilename() {
        return "bullet_laser.gif";
    }
}