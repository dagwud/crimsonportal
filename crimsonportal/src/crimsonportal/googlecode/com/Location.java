/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public class Location 
{
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
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
    
    protected int x;
    protected int y;
}
