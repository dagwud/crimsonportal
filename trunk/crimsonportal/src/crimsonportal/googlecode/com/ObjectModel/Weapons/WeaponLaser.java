package crimsonportal.googlecode.com.ObjectModel.Weapons;

import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.Weapons.BulletPistol;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameTime;
import crimsonportal.googlecode.com.ObjectModel.UnitWithWeapon;
import crimsonportal.googlecode.com.ObjectModel.Weapon;

/**
 *
 * @author jdevenish
 */
public class WeaponLaser extends Weapon {

    public WeaponLaser() {
        super();
    }
    
    public WeaponLaser(int clipSize, double attackSpeed) {
        super(clipSize, attackSpeed);
    }
    
    @Override
    public Bullet spawnBullet(UnitWithWeapon shooter, GameObject target, GameTime spawnTime) {
        return new BulletLaser(shooter, target, spawnTime);
    }

    @Override
    public Weapon clone() {
        return new WeaponLaser(getClipSize(), getAttackSpeed());
    }
    
    public int getDefaultClipSize() {
        return 10;
    }
    
    public double getDefaultAttackSpeed() {
        return 0.003d;
    }
}
