/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.GameController;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import crimsonportal.googlecode.com.Proxy.Sprite;
import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class GameCanvas extends JPanel implements Observer, Runnable
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
        try
        {
            // Draw enemies:
            Iterator<EnemyUnit> enemies = gameController.getGameState().getEnemies();
            while (enemies.hasNext())
            {
                Sprite img = spriteProxy.get("enemy.gif"); 
                GameObject enemy = enemies.next();
                g.drawImage(img.toImage(),
                        enemy.getLocation().getX() - (enemy.getSize() / 2),
                        enemy.getLocation().getY() - (enemy.getSize() / 2),
                        enemy.getSize(), enemy.getSize(), null);
            }
            
            // Draw players:
            Iterator<PlayerUnit> players = gameController.getGameState().getPlayers();
            while (players.hasNext())
            {
                Sprite img = spriteProxy.get("player.gif"); 
                GameObject player = players.next();
                g.drawImage(img.toImage(), 
                        player.getLocation().getX() - (player.getSize() / 2),
                        player.getLocation().getY() - (player.getSize() / 2),
                        player.getSize(), player.getSize(), null);
            }
        }
        catch (ConcurrentModificationException e)
        {
            // Happens when one of the collections (players, enemies, etc) is being
            // updated while this thread is trying to read it. Ignore the error, 
            // since the next call to paintComponent() will repaint.
        }
    }
    
    public void update(Observable o, Object arg)
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
