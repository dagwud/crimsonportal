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
public class WeaponPistol extends Weapon {

    public WeaponPistol() {
        super();
    }
    
    public WeaponPistol(int clipSize, double attackSpeed) {
        super(clipSize, attackSpeed);
    }
    
    @Override
    public Bullet spawnBullet(UnitWithWeapon shooter, GameObject target, GameTime gameTime) {
        return new BulletPistol(shooter, target, gameTime);
    }

    @Override
    public Weapon clone() {
        return new WeaponPistol(getClipSize(), getAttackSpeed());
    }
    
    public int getDefaultClipSize() {
        return 10;
    }
    
    public double getDefaultAttackSpeed() {
        return 0.003d;
    }
}
