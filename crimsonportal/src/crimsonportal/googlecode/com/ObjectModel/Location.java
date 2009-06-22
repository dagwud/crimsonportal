/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class Location
{
    public Location(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX()
    {
        return x;
    }
    
    protected void setX(double x)
    {
        this.x = x;
    }
    
    public double getY()
    {
        return y;
    }
    
    protected void setY(double y)
    {
        this.y = y;
    }
    
    @Override
    public Location clone()
    {
        Location l = new Location(x, y);
        return l;
    }
    
    public boolean equals(Location rhs)
    {
        if (rhs.x == x && rhs.y == y) 
        {
            return true;
        }
        return false;
    }
    
    public String toString() 
    {
        return "Location[" + y + "," + x + "]";
    }
    
    private double x;
    private double y;
}
