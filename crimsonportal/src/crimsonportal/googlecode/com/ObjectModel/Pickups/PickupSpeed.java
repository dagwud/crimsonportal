/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.Pickups;

import crimsonportal.googlecode.com.ObjectModel.*;

/**
 *
 * @author dagwud
 */
public class PickupSpeed extends PickupTimed
{
    protected static final double SIZE = PRESET_SIZE_SMALL;

    public PickupSpeed(Location location, GameTime expirationTime, 
            double speedMultiplier) {
        super(SIZE, location, expirationTime);
        this.speedMultiplier = speedMultiplier;
    }
    
    public void applyTo(GameTime gameTime, Unit unit)
    {
        startExpirationTimer(gameTime, unit);
        PickupProxy.scaleUnitMoveSpeed(unit, speedMultiplier);
    }
    
    public void unapplyTo(Unit unit)
    {
        PickupProxy.scaleUnitMoveSpeed(unit, 1.0 / speedMultiplier);
    }
    
    public int getEffectDurationSeconds() {
        return EFFECT_DURATION_SECONDS;
    }
    
    @Override
    public PickupSpeed clone()
    {
        return new PickupSpeed(location, getExpirationTime(), speedMultiplier);
    }
    
    /**
     * Specifies the name of the filename which represents the graphical 
     * representation of this game object. 
     * For instances of the PickupSpeed class, this returns the filename representing
     * the image used by GUIs to render a Speed pickup (that is, the filename of the
     * image which looks like a Speed pickup)
     * <p>
     * Note that the specifics of this rendering process are not defined here, but are 
     * deferred to the presentation-related classes.
     * @return a string representing the filename which is used by GUIs to determine
     * the sprite for Speed pickups
     */
    public String getSpriteFilename()
    {
        return "pickup_speed.gif";
    }
    
    public String toString() {
        return "SpeedPickup[x" + speedMultiplier + " for " + getEffectDurationSeconds() + "s]";
    }
    
    protected double speedMultiplier;
    protected static final int EFFECT_DURATION_SECONDS = 10;
}
