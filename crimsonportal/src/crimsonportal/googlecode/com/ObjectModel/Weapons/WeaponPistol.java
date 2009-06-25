package crimsonportal.googlecode.com.ObjectModel.Weapons;

import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.Strategy;
import crimsonportal.googlecode.com.ObjectModel.UnitWithWeapon;
import crimsonportal.googlecode.com.ObjectModel.Weapon;

/**
 *
 * @author jdevenish
 */
public class WeaponPistol extends Weapon {

    public WeaponPistol() {
        super();
    }
    
    public WeaponPistol(int clipSize, float firingRate) {
        super(clipSize, firingRate);
    }
    
    @Override
    public Bullet spawnBullet(UnitWithWeapon shooter, GameObject target) {
        return new BulletPistol(shooter, target);
    }

    @Override
    public Weapon clone() {
        return new WeaponPistol(getClipSize(), getFiringRate());
    }
    
    public int getDefaultClipSize() {
        return 10;
    }
    
    public float getDefaultFiringRate() {
        return 0.2f;
    }
    
    class BulletPistol extends Bullet {
        public BulletPistol(UnitWithWeapon shooter, GameObject target) {
            super(bulletRadius, shooter, shooter.getCentreOfObject(), 
                    new Strategy(target), DEFAULT_MOVE_SPEED, attackDamage);
        }
        static final double bulletRadius = 4;
        static final double DEFAULT_MOVE_SPEED = 10;
        static final int attackDamage = 1;

        @Override
        public double getDefaultMoveSpeed() {
            return DEFAULT_MOVE_SPEED;
        }

        @Override
        public Bullet clone() {
            return new BulletPistol(shooter, getStrategy().getTarget());
        }
    }
}
