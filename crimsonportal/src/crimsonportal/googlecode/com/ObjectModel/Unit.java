/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class Unit extends GameObject
{
    public Unit(double size, Location location, Strategy strategy)
    {
        super(size, location);
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
