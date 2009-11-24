package crimsonportal.googlecode.com.ObjectModel.Weapons;

import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameTime;
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

    public WeaponUzi(int clipSize, double attackSpeed) {
        super(clipSize, attackSpeed);
    }

    @Override
    public Bullet spawnBullet(UnitWithWeapon shooter, GameObject target, GameTime spawnTime) {
        return new BulletUzi(shooter, target, spawnTime);
    }

    @Override
    public Weapon clone() {
        return new WeaponUzi(getClipSize(), getAttackSpeed());
    }

    public int getDefaultClipSize() {
        return 50;
    }

    public double getDefaultAttackSpeed() {
        return 0.003d;
    }
}
