/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class SpeedPickup extends TimedPickup
{
    public SpeedPickup(Location location, GameTime expirationTime, 
            double speedMultiplier) {
        super(location, expirationTime);
        this.speedMultiplier = speedMultiplier;
    }
    
    public void applyTo(GameTime gameTime, Unit unit)
    {
        startExpirationTimer(gameTime, unit);
        double moveSpeed = (double)unit.getMoveSpeed() * speedMultiplier;
        unit.setMoveSpeed( moveSpeed );
    }
    
    public void unapplyTo(Unit unit)
    {
        double moveSpeed = (double)unit.getMoveSpeed() / speedMultiplier;
        unit.setMoveSpeed( moveSpeed );
    }
    
    public int getEffectDurationSeconds() {
        return EFFECT_DURATION_SECONDS;
    }
    
    @Override
    public SpeedPickup clone()
    {
        return new SpeedPickup(location, expirationTime, speedMultiplier);
    }
    
    public String getSpriteFilename()
    {
        return "pickup.gif";
    }
    
    public String toString() {
        return "SpeedPickup[x" + speedMultiplier + " for " + effectDurationSeconds + "s]";
    }
    
    protected double speedMultiplier;
    protected static final int EFFECT_DURATION_SECONDS = 10;
}
