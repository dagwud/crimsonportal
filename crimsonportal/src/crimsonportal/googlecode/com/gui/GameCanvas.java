/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.GameController;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedEvent;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.Shoot.ShootEvent;
import crimsonportal.googlecode.com.Observer.Player.Shoot.PlayerShootObservable;
import crimsonportal.googlecode.com.Proxy.Sprite;
import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import crimsonportal.googlecode.com.terrain.Terrain;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.drawImage(gameController.getGameState().getMap().getBGImage(), 0, 0, null);
        
        Terrain terrain = gameController.getGameState().getTerrain();
        
        try
        {
            // Draw objects:
            Iterator<GameObject> gameObjects = gameController.getGameState().getGameObjects();
            while (gameObjects.hasNext())
            {
                GameObject gameObject = gameObjects.next();
                Sprite img = spriteProxy.get(gameObject.getSpriteFilename()); 
                
                // Cache the scaling, rotation, etc. values in case they're changed
                // by another thread while we're drawing:
                Location objectLoc = gameObject.getCentreOfObject();
                double translateX = objectLoc.getX();
                double translateY = objectLoc.getY();
                double rotation = gameObject.getRotation();
                double imgRadius = gameObject.getSize() / 2.0;
                
                double heightPerc = (double)terrain.getHeightAt(objectLoc, 
                        gameController.getGameState().getMap()) / (double)terrain.getPeakHeight();
                double heightScale = (heightPerc * 0.7) + 0.8;
                double imgScale = (double)gameObject.getSize() / (double)img.toImage().getHeight();
                
                // Draw the GameObject: 
                
                // Move to centre of object:
                g2.translate(translateX, translateY);
                  // Rotate around the centre of the object:
                  g2.rotate(rotation);
                    // Scale to show height:
                    g2.scale(heightScale, heightScale);
                      // Move to the top-left corner of the object:
                      g2.translate(-imgRadius, -imgRadius);
                        // Scale to the image's size:
                        g2.scale(imgScale, imgScale);
                          // Draw the image:
                          g2.drawImage(img.toImage(), 0, 0, null);
                        g2.scale(1.0 / imgScale, 1.0 / imgScale);
                      g2.translate(imgRadius, imgRadius);
                    g2.scale(1.0 / heightScale, 1.0 / heightScale);
                  g2.rotate(-rotation);
                g2.translate(-translateX, -translateY);
            }
            
            // Draw the animations:
            Iterator<Animation> animations = gameController.getGameState().getAnimations();
            while (animations.hasNext()) {
                Animation anim = animations.next();
                boolean animActive = anim.drawOnto(g2);
                if (!animActive) {
                    animations.remove();
                }
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
