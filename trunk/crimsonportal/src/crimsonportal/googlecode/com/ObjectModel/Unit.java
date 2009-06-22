/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.Player.Shoot.ShootEvent;

/**
 *
 * @author dagwud
 */
public abstract class Unit extends GameObject
{
    public Unit(double size, Location location, Strategy strategy, GameState gameState)
    {
        super(size, location);
        this.strategy = strategy;
        this.gameState = gameState;
    }
    
    public Strategy getStrategy()
    {
        return strategy;
    }
    
    protected void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
    
    public double getHealth()
    {
        return health;
    }
    
    protected void setHealth(double health)
    {
        this.health = health;
    }
    
    public void moveTo(Location newLocation) 
    {
        Location moveTo = gameState.getTerrain().getMoveWithGradient(
                getCentreOfObject(), 
                newLocation, gameState.getMap());
        
        setCentreOfObject(moveTo);
    }
    
    protected Strategy strategy;
    protected double health;
    protected GameState gameState;
}
