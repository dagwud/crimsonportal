package crimsonportal.googlecode.com.ObjectModel.Pickups;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameTime;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.PickupProxy;
import crimsonportal.googlecode.com.ObjectModel.PickupSingleUse;
import crimsonportal.googlecode.com.ObjectModel.Unit;
import crimsonportal.googlecode.com.ObjectModel.UnitWithWeapon;
import crimsonportal.googlecode.com.gui.Animations.NukeAnimation;
import java.util.Iterator;

/**
 *
 * @author jdevenish
 */
public class PickupNuke extends PickupSingleUse {
    protected static final double SIZE = PRESET_SIZE_SMALL;
    
    public PickupNuke(Location location, GameTime expirationTime) {
        super(SIZE, location, expirationTime);
    }
    
    @Override
    public void applyTo(GameTime gameTime, Unit unit) {
        Iterator<EnemyUnit> units = PickupProxy.getEnemiesNear(this, NUKE_RADIUS);
        PickupProxy.addAnimation(new NukeAnimation(this.getCentreOfObject(), (int)NUKE_RADIUS));
        while (units.hasNext()) {
            EnemyUnit enemyUnit = units.next();
            PickupProxy.killUnit(enemyUnit, (UnitWithWeapon)unit);
        }
    }

    /**
     * Specifies the name of the filename which represents the graphical 
     * representation of this game object. 
     * For instances of the PickupNuke class, this returns the filename representing
     * the image used by GUIs to render a Nuke pickup (that is, the filename of the
     * image which looks like a Nuke pickup)
     * <p>
     * Note that the specifics of this rendering process are not defined here, but are 
     * deferred to the presentation-related classes.
     * @return a string representing the filename which is used by GUIs to determine
     * the sprite for Nukes
     */
    @Override
    public String getSpriteFilename() {
        return "pickup_nuke.gif";
    }

    private static final double NUKE_RADIUS = 300;
}
