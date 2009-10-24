/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.ObjectModel.Weapons.UnitWithArmour;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveEvent;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveObservable;

/**
 *
 * @author dagwud
 */
public class PlayerUnit extends UnitWithWeapon implements
        UnitWithArmour,
        PlayerMoveObservable, 
        Observer<PlayerTurnEvent>
{
    protected double DEFAULT_HEALTH = 100;
    protected static double PLAYER_SIZE = 8;
    protected static double DEFAULT_MOVE_SPEED = 10;
    
    public PlayerUnit(Location location, int moveSpeed, GameState gameState)
    {
        super(PLAYER_SIZE, location, null, gameState, null);
        observers = new ObserverGroup<PlayerMoveEvent>();
        turnObservers = new ObserverGroup<PlayerTurnEvent>();
        this.moveSpeed = moveSpeed;
        setHealth(DEFAULT_HEALTH);
    }
    
    public PlayerUnit(Location location, double moveSpeed, Weapon weapon, GameState gameState)
    {
        super(PLAYER_SIZE, location, null, gameState, null);
        setWeapon(weapon);
    }
    
    @Override
    public PlayerUnit clone()
    {
        PlayerUnit p = new PlayerUnit(getCentreOfObject(), moveSpeed, getWeapon(), gameState);
        return p;
    }
    
    public String getSpriteFilename()
    {
        return "player.gif";
    }
    
    private ObserverGroup<PlayerMoveEvent> observers;
    private ObserverGroup<PlayerTurnEvent> turnObservers;

    public MovementHandler getMovementHandler() {
        return new MovementHandlerPlayer(this);
    }
    
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
    
    public double getDefaultHealth() {
        return DEFAULT_HEALTH;
    }
    
    public void update(PlayerTurnEvent e) 
    {
        this.setRotation(e.getRotation());
    }
    
    @Override
    public String toString() {
        return "PlayerUnit[]";
    }

    public double getArmourPercentage()
    {
        return armourPerc;
    }

    public void setArmourPercentage(double perc)
    {
        armourPerc = perc;
    }

    public void reduceArmour(double percentagePoints)
    {
        this.armourPerc = Math.max(armourPerc - percentagePoints, 0);
    }

    public double getArmourStrength()
    {
        return armourStrength;
    }

    public void setArmourStrength(double armourStrength)
    {
        this.armourStrength = armourStrength;
    }
    
    public int getExperience() {
        return experience;
    }
    
    public void setExperience(int experience) {
        this.experience = experience;
    }
    
    protected double moveSpeed;
    protected double armourStrength;
    protected double armourPerc;
    protected int experience;
}
