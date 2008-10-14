/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class Strategy 
{
    public Strategy(Location target)
    {
        this.target = target;
    }
    
    public Location getTarget()
    {
        return target;
    }
    
    protected void setTarget(Location target)
    {
        this.target = target;
    }
    
    @Override
    public Strategy clone()
    {
        Strategy s = new Strategy(target);
        return s;
    }
    
    private Location target;
}
