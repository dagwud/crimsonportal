/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectModel.crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public abstract class Unit extends GameObject
{
    public Unit(Location location, Strategy strategy)
    {
        super(location);
        this.strategy = strategy;
    }
    
    public Strategy getStrategy()
    {
        return strategy;
    }
    
    protected void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    protected void setHealth(int health)
    {
        this.health = health;
    }
    
    private Strategy strategy;
    private int health;
}
