package crimsonportal.googlecode.com.ObjectModel;

/**
 * An abstract class which will be inherited from by any Pickup which can be 
 * picked up by a Unit and which provides a 'Single Use' pickup - that is, it
 * is applied to a Unit and is never unapplied. For example, subclasses of the 
 * <code>PickupSingleUse</code> class might include pickups which permanently
 * increase a Unit's movement speed, or which give a once-off increase to a Unit's
 * health
 * <p>
 * Inheriting subclasses will need to implement all the required methods in order 
 * to provide the specifics of the pickup, while this abstract class hides some
 * unnecessary detail in the inherited {@link Pickup} abstract class, such as the 
 * implementation of the {@link #unapplyTo unapplyTo} method.
 * <p>
 * The following points should be considered when writing inheriting classes:
 * <ul>
 * <li>All overriding classes <b>must</b> be sure to call the {@link #PickupSingleUse(Location, GameTime)}
 * constructor specified by this abstract class</li>
 * <li>The functionality of the pickup (that is, the handling of what actions are
 * performed when a Pickup is collected by a unit) must be provided by the inheriting
 * class in the {@link #applyTo applyTo} method, which will be called when the Pickup is collected.
 * </li>
 * <li>The {@link Pickup#unapplyTo} method is hidden from inheriting classes by this class,
 * and so it will not be possible for inheriting pickups to have any 'undo' effects.
 * If this behaviour is not desired, it may be better to consider extending from
 * the {@link PickupTimed} class in stead of from PickupSingleUse
 * </li>
 * </ul>
 * @author dagwud
 */
public abstract class PickupSingleUse extends Pickup {

    public PickupSingleUse(Location location, GameTime expirationTime) {
        super(location, expirationTime);
    }
    
    /**
     * Defines the actions to be taken when the effects of this Pickup are removed 
     * from a Unit. This method does nothing, but serves to hide the abstract
     * method in the underlying {@link Pickup} class from inheriting classes.
     * @see Pickup#unapplyTo
     * @param unit the Unit which had collected the Pickup and which the pickup
     * should now be removed from
     */
    @Override
    public final void unapplyTo(Unit unit) {}
}
