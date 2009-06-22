package crimsonportal.googlecode.com.ObjectModel;

/**
 * Representation of a location (that is, a position which has an X and Y 
 * coordinate). This is similar to java's built-in Dimension object, except
 * that the coordinates are expressed as doubles
 * @author dagwud
 */
public class Location
{
    /**
     * Creates a new Location with given coordinates. Note that the coordinates
     * are given a context by the way in which they are used - for example, a
     * Location can be given coordinates of (10,20) but the scale of the coordinates
     * is defined by the way in which it is used, not by the Location itself.
     * @param x the X-coordinate (horizontal position, on the X-axis) of the new location
     * @param y the Y-coordinate (vertical position, on the Y-axis) of the new location
     */
    public Location(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns the X-coordinate (horizontal position) of this location
     * @return this location's position on the X-axis
     */            
    public double getX()
    {
        return x;
    }
    
    /**
     * Sets the X-coordinate (horizontal position) of this location
     * @param x the new position of this location on the X-axis
     */
    protected void setX(double x)
    {
        this.x = x;
    }
    
    /**
     * Returns the Y-coordinate (vertical position) of this location
     * @return this location's position on the Y-axis
     */
    public double getY()
    {
        return y;
    }
    
    /**
     * Sets the Y-coordinate (vertical position) of this location
     * @param y the new position of this location on the Y-axis
     */
    protected void setY(double y)
    {
        this.y = y;
    }
    
    /**
     * Creates a new location which is identical to the current location,
     * that is, it has the same X- and Y-coordinates
     * @return an exact copy of the location <code>this</code>
     */
    @Override
    public Location clone()
    {
        Location l = new Location(x, y);
        return l;
    }
    
    /**
     * Tests whether two locations are identical, that is whether they have
     * the same X- and Y-coordinates
     * @param rhs the location which should be compared (to <code>this</code>)
     * @return true if the given location is identical to this location, false 
     * otherwise
     */
    public boolean equals(Location rhs)
    {
        if (rhs.x == x && rhs.y == y) 
        {
            return true;
        }
        return false;
    }
    
    /**
     * Returns this object converted to a String representation. Returns
     * a string which contains "Location[" + Y-coordinate + "," + X-coordinate + "]"
     * @return a String representation of this location
     */
    @Override
    public String toString() 
    {
        return "Location[" + y + "," + x + "]";
    }
    
    /**
     * The X-coordinate of this location (that is, this location's horizontal position)
     */
    private double x;

    /**
     * The Y-coordinate of this location (that is, this location's vertical position)
     */
    private double y;
}
