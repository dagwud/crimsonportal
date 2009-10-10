/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.Pickups;

import crimsonportal.googlecode.com.ObjectModel.*;
import crimsonportal.googlecode.com.ObjectModel.Weapons.UnitWithArmour;

/**
 *
 * @author dagwud
 */
public class PickupArmour extends PickupSingleUse {

    public PickupArmour(Location location, GameTime expirationTime) {
        super(location, expirationTime);
        this.armourStrength = 15;
    }
    
    @Override
    public void applyTo(GameTime gameTime, Unit unit)
    {
        UnitWithArmour u = (UnitWithArmour)unit;
        PickupProxy.setUnitArmour(u, this);
    }
    
    @Override
    public String getSpriteFilename()
    {
        return "pickup_armour.gif";
    }

    @Override
    public String toString() {
        return "PickupArmour[" + armourStrength + "]";
    }
    
    public double getStrength() {
        return armourStrength;
    }
    
    protected double armourStrength;
}
