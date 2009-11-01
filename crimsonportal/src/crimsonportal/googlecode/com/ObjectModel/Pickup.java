package crimsonportal.googlecode.com.ObjectModel;

/**
 * An abstract class which will be inherited from (indirectly) by any object 
 * which can be picked up by a Unit. 
 * <p>
 * Inheriting subclasses will need to implement
 * all the required methods in order to provide the specifics of the pickup, 
 * while the basic functionality common to all pickups (such as the mechanism 
 * for the expiration of a pickup - that is, when it disappears and is no longer 
 * collectable if it is not collected after a certain amount of time) will 
 * always be handled by this abstract class.
 * <p>
 * The following points should be considered when writing inheriting classes:
 * <ul>
 * <li>All overriding classes <b>must</b> be sure to call the {@link #Pickup(Location, GameTime)}
 * constructor specified by this abstract class</li>
 * <li>The functionality of the pickup (that is, the handling of what actions 
 * are performed when a Pickup is collected by a unit) must be provided by the 
 * inheriting class in the {@link #applyTo applyTo} method, which will be called 
 * when the Pickup is collected.
 * </li>
 * <li>Likewise, the {@link #unapplyTo unapplyTo} method must be provided by 
 * inheriting classes to provide the details of what actions are taken when the 
 * effects of a Pickup are to be removed from a Player (for example, if the 
 * Pickup is dropped, it's effect runs out, etc)
 * </li>
 * </ul>
 * @author dagwud
 */
public abstract class Pickup extends GameObject
{   
    /**
     * Creates a Pickup at a given location, and sets its expiration time (the
     * time at which the pickup will disappear if it is not collected). 
     * <p>
     * This method <b>must</b> be called by all constructors in all inherited 
     * classes.
     * @param location the location at which the pickup should be created, 
     * specified as the centre of the pickup
     * @param expirationTime the GameTime at which the pickup will expire - that 
     * is, the GameTime at which the pickup will disappear if it has not been 
     * collected
     */
    public Pickup(double radius, Location location, GameTime expirationTime)
    {
        super(radius, location);
        this.expirationTime = expirationTime;
    }
    
    /**
     * Returns the time at which this pickup will expire (that is, disappear) if
     * it is not collected
     * @return the time after which this pickup is no longer available to be 
     * collected
     */
    public final GameTime getExpirationTime()
    {
        return expirationTime;
    }
    
    /**
     * Sets the time at which this pickup will expire (that is, disappear) if it
     * is not collected
     * @param expirationTime the time after which this pickup should no longer 
     * be available to be collected
     */
    protected final void setExpirationTime(GameTime expirationTime)
    {
        this.expirationTime = expirationTime;
    }
    
    /**
     * Defines the actions to be taken when this Pickup is collected by a Unit.
     * Overriding methods must be sure to implement any effects of the pickup
     * in this method, which will be called when the Pickup is collected. The
     * effects of this pickup, if it is not permanent, should be reversed in the
     * {@link #unapplyTo(Unit)} method. 
     * <p>
     * <b>Note</b>: To maintain the appropriate protection of the GameModel package
     * classes, overriding methods should make use of the {@link PickupProxy} class in 
     * order to modify the given Unit
     * @param gameTime will be set to contain the time at which the collection
     * of the pickup happened (that is, the current game time at the time the
     * applyTo method is called)
     * @param unit the Unit which is collecting the Pickup
     */
    public abstract void applyTo(GameTime gameTime, Unit unit);
    
    /**
     * Defines the actions to be taken when the effects of this Pickup are removed 
     * from a Unit.
     * Overriding methods must be sure to implement the 'undoing' of any effects 
     * of the pickup in this method, which will be called when the Pickup is
     * removed from a Unit (if this is applicable), for example if the pickup
     * runs out or is discarded. 
     * <p>
     * <b>Note</b>: To maintain the appropriate protection of the GameModel package
     * classes, overriding methods should make use of the {@link PickupProxy} class in 
     * order to modify the given Unit
     * @param unit the Unit which had collected the Pickup and which the pickup
     * should now be removed from
     */
    public abstract void unapplyTo(Unit unit);
    
    /**
     * The time at which the pickup will expire (that is, disappear and no longer
     * be collectable) if it is not collected
     */
    private GameTime expirationTime;
}
