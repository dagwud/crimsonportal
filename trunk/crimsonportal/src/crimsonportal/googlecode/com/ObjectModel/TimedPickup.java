/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class TimedPickup extends Pickup {
    public TimedPickup(Location location, GameTime expirationTime, 
            int effectDurationSeconds) {
        super(location, expirationTime);
        this.effectDurationSeconds = effectDurationSeconds;
    }
    
    @Override
    public abstract void applyTo(GameTime gameTime, Unit unit);

    @Override
    public abstract void unapplyTo(Unit unit);

    @Override
    public abstract String getSpriteFilename();

    protected int effectDurationSeconds;
}
