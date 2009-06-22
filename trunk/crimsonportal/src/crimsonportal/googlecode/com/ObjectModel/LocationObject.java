package crimsonportal.googlecode.com.ObjectModel;

/**
 * A wrapper class which encapsulates a {@link Location} within a
 * {@link GameObject} to allow 
 * @author dagwud
 */
public class LocationObject extends GameObject 
{
    public LocationObject(Location location)
    {
        super(0.0d, location);
    }
    
    public LocationObject(double x, double y)
    {
        super(0d, new Location(x, y));
    }
    
    private LocationObject(double size, Location location)
    {
        super(size, location);
    }
    
    @Override
    public String getSpriteFilename()
    {
        return null;
    }

}
