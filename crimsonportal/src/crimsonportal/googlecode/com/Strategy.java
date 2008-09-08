/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

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
    
    public void setTarget(Location target)
    {
        this.target = target;
    }
    
    @Override
    public Strategy clone()
    {
        Strategy s = new Strategy(target);
        return s;
    }
    
    protected Location target;
}
