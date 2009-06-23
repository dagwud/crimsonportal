/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.GameSettings.ObjectSizes;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveEvent;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveObservable;

/**
 *
 * @author dagwud
 */
public class PlayerUnit extends Unit implements PlayerMoveObservable, 
        Observer<PlayerTurnEvent>
{
    protected double DEFAULT_HEALTH = 100;
    
    public PlayerUnit(Location location, int moveSpeed, GameState gameState)
    {
        super(ObjectSizes.PLAYER_SIZE, location, null, gameState);
        observers = new ObserverGroup<PlayerMoveEvent>();
        turnObservers = new ObserverGroup<PlayerTurnEvent>();
        this.moveSpeed = moveSpeed;
        setHealth(DEFAULT_HEALTH);
    }
    
    public PlayerUnit(Location location, double moveSpeed, Weapon weapon, GameState gameState)
    {
        super(ObjectSizes.PLAYER_SIZE, location, null, gameState);
        this.weapon = weapon;
        this.moveSpeed = moveSpeed;
    }
    
    public Weapon getWeapon()
    {
        return weapon;
    }
    
    protected void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
    
    @Override
    public PlayerUnit clone()
    {
        PlayerUnit p = new PlayerUnit(getCentreOfObject(), moveSpeed, weapon, gameState);
        return p;
    }
    
    public String getSpriteFilename()
    {
        return "player.gif";
    }
    
    private Weapon weapon;
    private ObserverGroup<PlayerMoveEvent> observers;
    private ObserverGroup<PlayerTurnEvent> turnObservers;

    public void notifyObservers(PlayerMoveEvent event)
    {
        Debug.logMethod("Player " + this + " has received a notification and is moving");
        
        if (observers.countObservers() > 0)
        {
            Debug.logMethod("Player " + this + " is notifying observers");
            observers.notifyObservers(event);
        }
        gameState.update(event);
    }

    public boolean addObserver(Observer<PlayerMoveEvent> observer)
    {
        return observers.addObserver(observer);
    }

    public boolean removeObserver(Observer<PlayerMoveEvent> observer)
    {
        return observers.removeObserver(observer);
    }

    public void removeAllObservers()
    {
        observers.removeAllObservers();
    }
    
    public int countObservers()
    {
        return observers.countObservers();
    }

    public void notifyObservers(PlayerTurnEvent event)
    {
        if (observers.countObservers() > 0)
        {
            Debug.logMethod("Player " + this + " is notifying observers");
            turnObservers.notifyObservers(event);
        }
    }
    
    @Override
    public void moveTo(Location location)
    {
        if (location.equals(this.location)) return;
        int heightFrom = gameState.getTerrain().getHeightAt(this.location, gameState.getMap());
        int heightTo = gameState.getTerrain().getHeightAt(location, gameState.getMap());
        
        if (heightFrom < heightTo) {
            Debug.setFlag(Debug.flagKey.PLAYER_MOVEMENT_VERTICAL, Debug.flagValue.ASCENDING);
        }
        else if (heightFrom > heightTo) {
            Debug.setFlag(Debug.flagKey.PLAYER_MOVEMENT_VERTICAL, Debug.flagValue.DESCENDING);
        }
        else {
            Debug.setFlag(Debug.flagKey.PLAYER_MOVEMENT_VERTICAL, Debug.flagValue.LEVEL);
        }
        
        super.moveTo(location);
    }
    
    public void update(PlayerTurnEvent e) 
    {
        this.setRotation(e.getRotation());
    }
    
    public String toString() {
        return "PlayerUnit[]";
    }
}
