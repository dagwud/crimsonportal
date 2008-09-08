/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public abstract class Unit 
{
    public Strategy getStrategy()
    {
        return strategy;
    }
    
    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    protected Strategy strategy;
    protected int health;
}
