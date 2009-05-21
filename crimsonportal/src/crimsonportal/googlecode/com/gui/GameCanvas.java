/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.GameController;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.PlayerTurnEvent;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedEvent;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.ShootEvent;
import crimsonportal.googlecode.com.Observer.Player.PlayerShootObservable;
import crimsonportal.googlecode.com.Proxy.Sprite;
import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class GameCanvas extends JPanel implements Observer<GameStateChangedEvent>, 
        PlayerShootObservable, Runnable
{
    public GameCanvas(GameController gameController)
    {
        super();
        this.gameController = gameController;
        gameController.addObserver(this);
        setDoubleBuffered(true);
        spriteProxy = new SpriteProxy();
        setSize(800, 600);
        setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        try
        {
            // Draw objects:
            Iterator<GameObject> gameObjects = gameController.getGameState().getGameObjects();
            while (gameObjects.hasNext())
            {
                GameObject gameObject = gameObjects.next();
                Sprite img = spriteProxy.get(gameObject.getSpriteFilename()); 
                
                // Calculate the angle this gameobject is facing:
                double rotate = gameObject.getRotation();
                
                // Draw the GameObject: 
                double scale = gameObject.getSize() / img.toImage().getWidth();
                double translateX = gameObject.getCentreOfObject().getX();
                double translateY = gameObject.getCentreOfObject().getY();
                double translateSize = -gameObject.getSize() / 2.0;
                g2.translate(translateX, translateY);
                   g2.rotate(rotate);
                      g2.translate(translateSize, translateSize);
                         g2.scale(scale, scale);
                            g.drawImage(img.toImage(), 0, 0, null);
                         g2.scale(1.0 / scale, 1.0 / scale);
                      g2.translate(-translateSize, -translateSize);
                   g2.rotate(-rotate);
                g2.translate(-translateX, -translateY);
            }
            
            // Repaint the canvas with the new objects' positions:
            repaint();
        }
        catch (ConcurrentModificationException e)
        {
            // Happens when one of the collections (players, enemies, etc) is being
            // updated while this thread is trying to read it. Ignore the error, 
            // since the next call to paintComponent() will repaint.
        }
    }
    
    public void update(GameStateChangedEvent event)
    {
        repaint();
    }
    
    protected GameController getGameController()
    {
        return gameController;
    }
    
    public void run()
    {
        while (true)
        {
            repaint();
            try
            {
                Thread.sleep(1); // Allow other threads to execute!
            }
            catch (InterruptedException e)
            {
                // Do nothing
            }
        }
    }
        
    protected SpriteProxy spriteProxy;
    private GameController gameController;

    public void notifyObservers(ShootEvent event)
    {
        shootObservers.notifyObservers(event);
    }

    public boolean addObserver(Observer<ShootEvent> observer)
    {
        return shootObservers.addObserver(observer);
    }

    public boolean removeObserver(Observer<ShootEvent> observer)
    {
        return shootObservers.removeObserver(observer);
    }

    public void removeAllObservers()
    {
        shootObservers.removeAllObservers();
    }

    public int countObservers()
    {
        return shootObservers.countObservers();
    }
    
    private ObserverGroup<ShootEvent> shootObservers;
}
