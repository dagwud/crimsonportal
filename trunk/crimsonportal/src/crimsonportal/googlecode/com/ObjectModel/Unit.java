/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author dagwud
 */
public abstract class Unit extends GameObject
{
    public Unit() {
        super();
    }
    
    public Unit(Double size, Location location, Strategy strategy, 
            GameState gameState)
    {
        super(size, location);
        //if (strategy != null) {
            this.strategy = strategy;
        //}
        //if (gameState != null) {
            this.gameState = gameState;
        //}
        pickups = new LinkedList<Pickup>();
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
    
    protected abstract MovementHandler getMovementHandler();
    
    public void addPickup(Pickup pickup)
    {
        pickups.add(pickup);
        pickup.applyTo(gameState.getGameTime(), this);
    }
    
    public abstract double getExperienceValueToKiller();
    
    protected Strategy strategy;
    protected double health;
    protected GameState gameState;
    protected Collection<Pickup> pickups;
}
