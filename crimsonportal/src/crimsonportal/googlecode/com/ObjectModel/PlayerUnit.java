/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.GameSettings.ObjectSizes;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.PlayerMoveEvent;
import crimsonportal.googlecode.com.Observer.Player.PlayerMoveObservable;
import crimsonportal.googlecode.com.Observer.Player.PlayerTurnObservable;

/**
 *
 * @author dagwud
 */
public class PlayerUnit extends Unit implements PlayerMoveObservable, 
        Observer<PlayerTurnEvent>
{
    public PlayerUnit(Location location, int moveSpeed)
    {
        super(ObjectSizes.PLAYER_SIZE, location, null);
        observers = new ObserverGroup<PlayerMoveEvent>();
        turnObservers = new ObserverGroup<PlayerTurnEvent>();
        this.moveSpeed = moveSpeed;
    }
    
    public PlayerUnit(Location location, int moveSpeed, Weapon weapon)
    {
        super(ObjectSizes.PLAYER_SIZE, location, null);
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
    
    public int getMoveSpeed()
    {
        return moveSpeed;
    }
    
    protected void setMoveSpeed(int moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }
    
    @Override
    public PlayerUnit clone()
    {
        PlayerUnit p = new PlayerUnit(getCentreOfObject(), moveSpeed, weapon);
        return p;
    }
    
    public String getSpriteFilename()
    {
        return "player.gif";
    }
    
    private Weapon weapon;
    private int moveSpeed;
    private ObserverGroup<PlayerMoveEvent> observers;
    private ObserverGroup<PlayerTurnEvent> turnObservers;

    public void notifyObservers(PlayerMoveEvent event)
    {
        Debug.logMethod("Player " + this + " has received a notification and is moving");
        this.getCentreOfObject().setX(getCentreOfObject().getX() + event.getMoveAmountX());
        this.getCentreOfObject().setY(getCentreOfObject().getY() + event.getMoveAmountY());
        
        if (observers.countObservers() > 0)
        {
            Debug.logMethod("Player " + this + " is notifying observers");
            observers.notifyObservers(event);
        }
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
    
    public void update(PlayerTurnEvent e) 
    {
        this.setRotation(e.getRotation());
    }

    /*public boolean addObserver(Observer<PlayerTurnEvent> observer)
    {
        return turnObservers.addObserver(observer);
    }

    public boolean removeObserver(Observer<PlayerTurnEvent> observer)
    {
        return turnObservers.removeObserver(observer);
    }*/
}
