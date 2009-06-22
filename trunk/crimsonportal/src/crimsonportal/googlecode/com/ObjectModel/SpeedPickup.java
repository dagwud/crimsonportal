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
        super(location, expirationTime, 3);
        this.speedMultiplier = speedMultiplier;
    }
    
    public void applyTo(GameTime gameTime, Unit unit)
    {
        double moveSpeed = (double)unit.getMoveSpeed() * speedMultiplier;
        unit.setMoveSpeed( moveSpeed );
        // Create the timeout:
        GameTimerAction action = new PickupExpirationAction(this, unit);
        double numMillisecondsActive = gameTime.getNumMilliseconds() + (effectDurationSeconds * 1000.0);
        System.out.println(numMillisecondsActive);
        int numSecondsActive = (int) Math.floor(numMillisecondsActive / 1000.0);
        System.out.println(numSecondsActive);
        GameTime effectEndTime = new GameTime( numSecondsActive );
        System.out.println("end time is " + effectEndTime);
        GameTimer.getInstance().addTimer(effectEndTime, action);
    }
    
    public void unapplyTo(Unit unit)
    {
        double moveSpeed = (double)unit.getMoveSpeed() / speedMultiplier;
        unit.setMoveSpeed( moveSpeed );
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
}
