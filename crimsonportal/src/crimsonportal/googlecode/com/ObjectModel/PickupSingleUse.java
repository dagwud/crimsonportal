/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class PickupSingleUse extends Pickup {

    public PickupSingleUse(Location location, GameTime expirationTime) {
        super(location, expirationTime);
    }
    
    @Override
    public abstract void applyTo(GameTime gameTime, Unit unit);
    
    @Override
    public final void unapplyTo(Unit unit) {}

    @Override
    public abstract String getSpriteFilename();

}
