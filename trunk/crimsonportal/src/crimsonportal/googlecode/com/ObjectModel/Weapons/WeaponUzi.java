package crimsonportal.googlecode.com.ObjectModel.Weapons;

import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.Strategy;
import crimsonportal.googlecode.com.ObjectModel.UnitWithWeapon;
import crimsonportal.googlecode.com.ObjectModel.Weapon;

/**
 *
 * @author jdevenish
 */
public class WeaponUzi extends Weapon {

    public WeaponUzi() {
        super();
    }

    public WeaponUzi(int clipSize, float firingRate) {
        super(clipSize, firingRate);
    }

    @Override
    public Bullet spawnBullet(UnitWithWeapon shooter, GameObject target) {
        return new BulletUzi(shooter, target);
    }

    @Override
    public Weapon clone() {
        return new WeaponUzi(getClipSize(), getFiringRate());
    }

    public int getDefaultClipSize() {
        return 50;
    }

    public float getDefaultFiringRate() {
        return 0.003f;
    }

    class BulletUzi extends Bullet {
        public BulletUzi(UnitWithWeapon shooter, GameObject target) {
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
            return new BulletUzi(shooter, getStrategy().getTarget());
        }
    }
}
