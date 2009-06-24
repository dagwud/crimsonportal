/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Controller;

import crimsonportal.googlecode.com.Observer.Player.Move.MoveTimer;
import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.GameSettings.ObjectSizes;
import crimsonportal.googlecode.com.ObjectModel.Bullet;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.LocationObject;
import crimsonportal.googlecode.com.ObjectModel.PlayerTurnEvent;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import crimsonportal.googlecode.com.ObjectModel.Strategy;
import crimsonportal.googlecode.com.ObjectModel.Unit;
import crimsonportal.googlecode.com.Observer.Observable;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.Shoot.ShootEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author dagwud
 */
public class ShootListener implements MouseListener, MouseMotionListener,
        Observable<ShootEvent>
{
    public ShootListener(Unit controlledUnit)
    {
        Debug.logMethod("Initialising ShootListener for unit " + controlledUnit);
        observers = new ObserverGroup<ShootEvent>();
        this.controlledUnit = controlledUnit;
    }
    
    
    public void notifyObservers(ShootEvent event)
    {
        if (observers.countObservers() > 0)
        {
            Debug.logMethod("ShootListener " + this + " is notifying observers");
            observers.notifyObservers(event);
        }
    }

    public void removeAllObservers()
    {
        observers.removeAllObservers();
    }
    
    public int countObservers()
    {
        return observers.countObservers();
    }
    
    public boolean addObserver(Observer<ShootEvent> observer)
    {
        return observers.addObserver(observer);
    }

    public boolean removeObserver(Observer<ShootEvent> observer)
    {
        return observers.removeObserver(observer);
    }
    
    private Unit controlledUnit;
    private ObserverGroup<ShootEvent> observers;

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) 
    {
        GameObject target = new LocationObject(e.getX(), e.getY());
        
        double bulletSize = ObjectSizes.BULLET_SIZE_PISTOL;
        double bulletMoveSpeed = ObjectSizes.BULLET_SPEED_PISTOL;
        int bulletDamage = ObjectSizes.BULLET_DAMAGE_PISTOL;
        Strategy strategy = new Strategy(target);
        Bullet bullet = new Bullet(bulletSize, controlledUnit,
                controlledUnit.getCentreOfObject(), 
                strategy, bulletMoveSpeed, bulletDamage);
        ShootEvent ev = new ShootEvent(true, controlledUnit, bullet);
        lastShot = ev;
        notifyObservers(ev);
    }
    
    public void mouseReleased(MouseEvent e) 
    {
        //GameObject target = new LocationObject(e.getX(), e.getY());
        
        /*double bulletSize = ObjectSizes.BULLET_SIZE_PISTOL;
        double bulletMoveSpeed = ObjectSizes.BULLET_SPEED_PISTOL;
        int bulletDamage = ObjectSizes.BULLET_DAMAGE_PISTOL;
        Strategy strategy = new Strategy(target);
        Bullet bullet = new Bullet(bulletSize,
                controlledUnit.getCentreOfObject(), 
                strategy, bulletMoveSpeed, bulletDamage);*/
        ShootEvent ev = new ShootEvent(false, controlledUnit, null);
        lastShot = ev;
        notifyObservers(ev);
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    public void mouseDragged(MouseEvent e)
    {
        if (lastShot == null) return;
        if (!lastShot.isStartEvent()) return;
        // Create a new bullet based on the last one shot:
        Bullet lastBullet = lastShot.getBullet();
        Strategy strategy = new Strategy(new Location(e.getX(), e.getY()));
        Bullet bullet = new Bullet(lastBullet.getRadius(), controlledUnit,
                controlledUnit.getCentreOfObject(),
                strategy, lastBullet.getMoveSpeed(),
                lastBullet.getAttackDamage());
        ShootEvent ev = new ShootEvent(true, controlledUnit, bullet);
        lastShot = ev;
        notifyObservers(ev);
    }
    
    ShootEvent lastShot = null;
}
