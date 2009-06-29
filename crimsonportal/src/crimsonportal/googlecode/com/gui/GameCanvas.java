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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
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
        
        // Minimum size: a the same size as the map
        setMinimumSize(gameController.getGameState().getMap().getSize());
        
        // Ideal size: full-screen :D
        Rectangle screenSize = new Rectangle(800,600);
        setPreferredSize(screenSize.getSize());
        setSize(getPreferredSize());
        
        setOpaque(false);
    }
    
    public BufferedImage animationBuffer;
        
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //if (1 == 1) return;
        
        if (animationBuffer == null) {
             animationBuffer = getGraphicsConfiguration().createCompatibleImage(
                getGraphicsConfiguration().getBounds().width,
                getGraphicsConfiguration().getBounds().height,
                Transparency.TRANSLUCENT);
        }
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
            
        Terrain terrain = gameController.getGameState().getTerrain();
        
        
        try
        {
            // Scale to convert map sizes to GUI sizes:
            double xScale = ((double)this.getWidth()
                    / (double)gameController.getGameState().getMap().getWidth());
            double yScale = ((double)this.getHeight() 
                    / (double)gameController.getGameState().getMap().getHeight());
            graphics.scale(xScale, yScale);
            
                // Draw background:
                graphics.drawImage(gameController.getGameState().getMap().getBGImage(), 0, 0, null);
        
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
                    double imgRadius = gameObject.getRadius();

                    double heightPerc = (double)terrain.getHeightAt(objectLoc, 
                            gameController.getGameState().getMap()) / (double)terrain.getPeakHeight();
                    double heightScale = (heightPerc * 0.7) + 0.8;
                    double imgScale = (double)(gameObject.getRadius() * 2.0) / (double)img.toImage().getHeight();

                    // Draw the GameObject: 

                    // Move to centre of object:
                    graphics.translate(translateX, translateY);
                      // Rotate around the centre of the object:
                      graphics.rotate(rotation);
                        // Scale to show height:
                        graphics.scale(heightScale, heightScale);
                          // Move to the top-left corner of the object:
                          graphics.translate(-imgRadius, -imgRadius);
                            // Scale to the image's size:
                            graphics.scale(imgScale, imgScale);
                              // Draw the image:
                              graphics.drawImage(img.toImage(), 0, 0, null);
                            graphics.scale(1.0 / imgScale, 1.0 / imgScale);
                          graphics.translate(imgRadius, imgRadius);
                        graphics.scale(1.0 / heightScale, 1.0 / heightScale);
                      graphics.rotate(-rotation);
                    graphics.translate(-translateX, -translateY);
                }

                // Start any unstarted animations:
                Iterator<Animation> animations = gameController.getGameState().getAnimations();
                if (animations.hasNext()) {

                    // Start new animations, and stop those which are complete:
                    while (animations.hasNext()) {
                        Animation anim = animations.next();
                        // Start unstarted animations:
                        if ( !anim.hasStarted() ) {
                            anim.setTargetGraphics2D(graphics);
                            Thread t = anim;
                            t.start();
                        }
                        //anim.targetGraphics = bufferedGraphics;
                        //anim.drawAnimation();
                        // Remove animations which have finished rendering:
                        if (anim.isAnimationComplete()) {
                            animations.remove();
                        }
                    }
                }

                // Draw the animations on the graphics buffer:
                graphics.drawImage(animationBuffer, null, 0, 0);
            
            // Return to normal scale:
            graphics.scale(1.0 / xScale, 1.0 / yScale);
            
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
