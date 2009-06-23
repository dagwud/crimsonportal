/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class PickupHealth extends PickupSingleUse {

    public PickupHealth(Location location, GameTime expirationTime, double healthValue) {
        super(location, expirationTime);
        this.healthValue = healthValue;
    }
    
    @Override
    public void applyTo(GameTime gameTime, Unit unit)
    {
        unit.setHealth( unit.getHealth() + healthValue );
    }
    
    @Override
    public String getSpriteFilename()
    {
        return "pickup_health.gif";
    }

    protected double healthValue;
}
