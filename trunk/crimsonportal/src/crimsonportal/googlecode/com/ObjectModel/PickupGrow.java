package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author jdevenish
 */
public class PickupGrow extends PickupTimed {
    public PickupGrow(Location location, GameTime expirationTime, double sizeMultiplier) {
        super(location, expirationTime);
        this.sizeMultiplier = sizeMultiplier;
    }
    
    @Override
    public void applyTo(GameTime expirationTime, Unit unit) {
        startExpirationTimer(expirationTime, unit);
        unit.setSize( unit.getSize() * sizeMultiplier );
    }
    
    @Override
    public void unapplyTo(Unit unit) {
        unit.setSize(unit.getSize() / sizeMultiplier);
    }

    @Override
    public String getSpriteFilename() {
        return "pickup_grow.gif";
    }

    @Override
    public int getEffectDurationSeconds() {
        return EFFECT_DURATION_SECONDS;
    }
    
    @Override
    public String toString() {
        return "HealthPickup[x" + sizeMultiplier + " for " + getEffectDurationSeconds() + "s]";
    }
    
    protected double sizeMultiplier;
    protected static final int EFFECT_DURATION_SECONDS = 10;
}
