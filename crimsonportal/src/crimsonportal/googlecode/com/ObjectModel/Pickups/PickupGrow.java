package crimsonportal.googlecode.com.ObjectModel.Pickups;

import crimsonportal.googlecode.com.ObjectModel.*;

/**
 *
 * @author jdevenish
 */
public class PickupGrow extends PickupTimed {
    protected static final double SIZE = PRESET_SIZE_SMALL;

    public PickupGrow(Location location, GameTime expirationTime, double sizeMultiplier) {
        super(SIZE, location, expirationTime);
        this.sizeMultiplier = sizeMultiplier;
    }
    
    @Override
    public void applyTo(GameTime expirationTime, Unit unit) {
        startExpirationTimer(expirationTime, unit);
        PickupProxy.scaleUnitSize(unit, sizeMultiplier);
    }
    
    @Override
    public void unapplyTo(Unit unit) {
        PickupProxy.scaleUnitSize(unit, 1.0 / sizeMultiplier);
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
        return "PickupGrow[x" + sizeMultiplier + " for " + getEffectDurationSeconds() + "s]";
    }
    
    protected double sizeMultiplier;
    protected static final int EFFECT_DURATION_SECONDS = 10;
}
