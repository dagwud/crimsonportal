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
import crimsonportal.googlecode.com.Proxy.Sprite;
import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class GameCanvas extends JPanel implements Observer<GameStateChangedEvent>, 
                                                    Runnable
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
        addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) 
            {
                // create playershoot event
            }
            
            public void mouseClicked(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
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
                g2.translate(Math.round(gameObject.getCentreOfObject().getX()), Math.round(gameObject.getCentreOfObject().getY()));
                   g2.rotate(rotate);
                      g2.translate(Math.round(-gameObject.getSize() / 2.0), Math.round(-gameObject.getSize() / 2.0));
                         g2.scale(scale, scale);
                            g.drawImage(img.toImage(), 0, 0, null);
                         g2.scale(1.0 / scale, 1.0 / scale);
                      g2.translate(Math.round(gameObject.getSize() / 2.0), Math.round(gameObject.getSize() / 2.0));
                   g2.rotate(-rotate);
                g2.translate(Math.round(-gameObject.getCentreOfObject().getX()), Math.round(-gameObject.getCentreOfObject().getY()));
            }
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
}
