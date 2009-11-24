/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.Weapons;

import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameTime;
import crimsonportal.googlecode.com.ObjectModel.UnitWithWeapon;
import crimsonportal.googlecode.com.ObjectModel.Weapon;

/**
 *
 * @author dagwud
 */
public class WeaponPunch extends Weapon {
    public WeaponPunch(int attackDamage, double attackSpeed) {
        super(0, attackSpeed);
        this.attackDamage = attackDamage;
    }
    
    public WeaponPunch clone() {
        WeaponPunch w = new WeaponPunch(attackDamage, attackSpeed);
        w.attackDamage = this.attackDamage;
        return w;
    }

    @Override
    public int getDefaultClipSize()
    {
        return 0;
    }

    @Override
    public double getDefaultAttackSpeed()
    {
        return attackSpeed;
    }

    @Override
    public Bullet spawnBullet(UnitWithWeapon shooter, GameObject target, GameTime gameTime)
    {
        return null;
    }
}
