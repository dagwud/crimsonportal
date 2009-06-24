/**
 * Contains all classes relating to the logical structure of the Game and the 
 * logical elements active in it, such as the players, units, pickups, etc.
 */
package crimsonportal.googlecode.com.ObjectModel;

/**
 * An abstract class which all game objects must extend, which provides 
 * storage for core properties as well as functionality common to all objects 
 * within the game scope.
 * @author dagwud
 */
public abstract class GameObject
{
    /**
     * Constructor which creates a game object with given minimum properties.
     * @param size the size (diameter) of the game object. Note that this is the
     * logical size, and is not necessarily the physical size which the game object
     * may be rendered by any particular GUI element.
     * @param location the location of the game object (given as a Location object
     * which represents the centre of the game object)
     */
    public GameObject(double size, Location location)
    {
        this.size = size;
        this.location = location.clone();
    }
    
    /**
     * Returns the current location of the centre of the game object
     * @return the position of the centre of the game object
     */
    public Location getCentreOfObject()
    {
        return location;
    }
    
    /**
     * Returns the size (diameter) of the game object. Note that this is the 
     * logical size, and is not necessarily the physical size which the game
     * object may be rendered by any particular GUI element.
     * @return the diameter of the game object
     * @see #setRadius
     */
    public double getRadius()
    {
        return size;
    }
    
    /**
     * Sets the size (diameter) of the game object. Note that this is the logical
     * size, and is not necessarily the physical size which the game object may
     * be rendered by any particular GUI element.
     * @param size the new diameter of the game object
     * @see #setRadius
     */
    protected void setRadius(double size) {
        this.size = size;
    }
    
    /**
     * Returns the rotation of this game object (that is, the angle that this
     * game object is facing).
     * @see #rotation details of the rotation value returned
     * @return the rotation of the game object, in radians.
     */
    public double getRotation()
    {
        return rotation;
    }
    
    /**
     * Rotates this game object to face a given direction
     * @param rotation the angle to face, in radians.
     * @see #rotation details of the rotation parameter
     */
    protected void setRotation(double rotation)
    {
        this.rotation = rotation;
    }
    
    /**
     * Sets the location of this game object. 
     * @param location the location representing the centre of the game object
     */
    protected void setCentreOfObject(Location location)
    {
        this.location = location;
    }
    
    /**
     * Tests whether this game object and another game object are so close that 
     * they are overlapping (that is, whether they are touching at all).
     * Note that calling <code>a.testOverlapsWith(b)</code> and 
     * <code>b.testOverlapsWith(a)</code> must return the same values in all situations.
     * @param obj the game object which should be checked for an overlap with the
     * current game objects
     * @return true if <code>this</code> and obj are overlapping, and false
     * if they are not
     */
    public boolean testOverlapsWith(GameObject obj)
    {
        // Calculate the X- and Y- components of the Euclidean distance between
        // the two objects:
        double xdiff = obj.getCentreOfObject().getX() - getCentreOfObject().getX();
        double ydiff = obj.getCentreOfObject().getY() - getCentreOfObject().getY();
        
        // Calculate the maximum Euclidean distance between these objects which 
        // would result in these two objects overlapping. This is the maximum 
        // equivalent of the sum of the two objects' radii:
        double overlapMaxDistance = obj.getRadius() + getRadius();
        
        // Get the actual Euclidean distance between the two objects:
        double distBetweenObjs = Math.sqrt((xdiff * xdiff) + (ydiff * ydiff));
        
        // Return true if the two objects are close enough to be overlapping, false
        // otherwise:
        return (distBetweenObjs <= overlapMaxDistance);
    }
    
    /**
     * Abstract method to be implemented by inheriting classes and which specifies
     * the name of the filename which represents the graphical representation of this
     * game object. For example, if a class called Box is implemented which
     * inherits from GameObject, and a Box is graphically represented by a file named
     * "Box.jpg" then this method might be implemented in the Box class to return 
     * the string "Box.jpg". This method allows instances of Game Objects, which 
     * are logical objects, to be represented in the presentation layer by having 
     * graphical classes query the Game Object's getSpriteFilename method and 
     * rendering that file.
     * Note that the specifics of this rendering process are not defined here, but are 
     * deferred to the presentation-related classes.
     * @return a string representing the filename which is used to find the sprite
     * for the overriding type of Game Object.
     */
    public abstract String getSpriteFilename();
    
    /**
     * The location of this game object, represented as the location at which 
     * the centre of the game object is logically positioned.
     * @see #getCentreOfObject()
     * @see #setCentreOfObject(Location)
     */
    protected Location location;
    
    /**
     * The size (diameter) of this game object
     * @see #setRadius()
     */
    protected double size;
    
    /**
     * The rotation of this object (that is, the direction this game object is facing) 
     * in radians.
     * Note that a value of 0 indicates the object is facing East, with increasing
     * values indicating a turn clockwise around the Z axis, such that:
     * <table>
     *  <tr><th>Rotation (radians)</th> <th>Degree equivalent</th> <th>Direction faced</th></tr>
     *  <tr><td>0</td>         <td>0&deg;</td>   <td>Due East</td> </tr>
     *  <tr><td>PI / 2</td>    <td>90&deg;</td>  <td>Due South</td></tr>
     *  <tr><td>PI</td>        <td>180&deg;</td> <td>Due West</td> </tr>
     *  <tr><td>3 * PI / 2</td><td>270&deg;</td> <td>Due North</td></tr>
     * </table>
     * @see #getRotation()
     * @see #setRotation(double)
     */
    protected double rotation;
}
