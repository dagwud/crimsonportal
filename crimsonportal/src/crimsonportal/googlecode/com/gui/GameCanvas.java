/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.GameController;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedEvent;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.Shoot.ShootEvent;
import crimsonportal.googlecode.com.Observer.Player.Shoot.PlayerShootObservable;
import crimsonportal.googlecode.com.Proxy.Sprite;
import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
        setSize(gameController.getGameState().getMap().getSize());
        setPreferredSize(gameController.getGameState().getMap().getSize());
        setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.drawImage(gameController.getGameState().getMap().getBGImage(), 0, 0, null);
        
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
                
                Location objectLoc = gameObject.getCentreOfObject();
                float height = 
                        gameController.getGameState().getTerrain().getHeightAt(
                            objectLoc.getY(), objectLoc.getX(), gameController.getGameState().getMap()
                        );
                height = height / getGameController().getGameState().getTerrain().getPeakHeight();
                
                // Draw the GameObject: 
                float scale = (float)gameObject.getSize() / (float)img.toImage().getWidth();
                scale = scale * ((height * 0.7f) + 0.8f);
                double translateX = gameObject.getCentreOfObject().getX();
                double translateY = gameObject.getCentreOfObject().getY();
                g2.translate(translateX, translateY);
                   g2.rotate(rotate);
                      g2.scale(scale, scale);
                         g2.translate(-gameObject.getSize(), -gameObject.getSize());
                            g.drawImage(img.toImage(), 0, 0, null);
                         g2.translate(gameObject.getSize(), gameObject.getSize());
                      g2.scale(1.0 / scale, 1.0 / scale);
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
