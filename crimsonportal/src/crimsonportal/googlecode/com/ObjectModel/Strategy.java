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
    public Strategy(GameObject target)
    {
        this.target = target;
    }
    
    public Strategy(Location target)
    {
        LocationObject objTarget = new LocationObject(target);
        this.target = objTarget;
    }
    
    public GameObject getTarget()
    {
        return target;
    }
    
    protected void setTarget(GameObject target)
    {
        this.target = target;
    }
    
    @Override
    public Strategy clone()
    {
        Strategy s = new Strategy(target);
        return s;
    }
    
    private GameObject target;
}
