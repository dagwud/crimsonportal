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
    public Unit(double size, Location location, Strategy strategy, GameState gameState)
    {
        super(size, location);
        this.strategy = strategy;
        this.gameState = gameState;
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
    
    public double getMoveSpeed()
    {
        return moveSpeed;
    }
    
    protected void setMoveSpeed(double moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }
    
    public void moveTo(Location newLocation) 
    {
        Location moveTo = gameState.getTerrain().getMoveWithGradient(
                getCentreOfObject(), 
                newLocation, gameState.getMap());
        
        setCentreOfObject(moveTo);
    }
    
    public void addPickup(Pickup pickup)
    {
        pickups.add(pickup);
        pickup.applyTo(gameState.getGameTime(), this);
    }
    
    protected Strategy strategy;
    protected double health;
    protected double moveSpeed;
    protected GameState gameState;
    protected Collection<Pickup> pickups;
}
