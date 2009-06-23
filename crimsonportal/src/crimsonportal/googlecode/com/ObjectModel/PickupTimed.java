/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class PickupTimed extends Pickup {
    public PickupTimed(Location location, GameTime expirationTime) {
        super(location, expirationTime);
    }
    
    @Override
    public abstract void applyTo(GameTime gameTime, Unit unit);

    @Override
    public abstract void unapplyTo(Unit unit);

    @Override
    public abstract String getSpriteFilename();
    
    protected final void startExpirationTimer(GameTime gameTime, Unit unitToUnapplyTo)
    {
        // Create the timeout:
        GameTimerAction action = new PickupExpirationAction(this, unitToUnapplyTo);
        double numMillisecondsActive = gameTime.getNumMilliseconds()
                + (getEffectDurationSeconds() * 1000.0);
        int numSecondsActive = (int) Math.floor(numMillisecondsActive / 1000.0);
        GameTime effectEndTime = new GameTime( numSecondsActive );
        GameTimer.getInstance().addTimer(effectEndTime, action);
    }
    
    public abstract int getEffectDurationSeconds();
}
