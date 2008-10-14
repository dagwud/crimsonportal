/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.GameController;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import crimsonportal.googlecode.com.ObjectModel.EnemyUnit;
import crimsonportal.googlecode.com.ObjectModel.GameObject;
import java.awt.Color;
import java.awt.Graphics;
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
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        try
        {
            // Draw players:
            Iterator<PlayerUnit> players = gameController.getGameState().getPlayers();
            while (players.hasNext())
            {
                GameObject player = players.next();
                g.setColor(Color.green);
                g.draw3DRect(player.getLocation().getX() - (player.getSize() / 2), 
                             player.getLocation().getY() - (player.getSize() / 2),
                             player.getSize(), player.getSize(), true);
            }

            // Draw enemies:
            Iterator<EnemyUnit> enemies = gameController.getGameState().getEnemies();
            while (enemies.hasNext())
            {
                GameObject enemy = enemies.next();
                g.setColor(Color.red);
                g.draw3DRect(enemy.getLocation().getX() - (enemy.getSize() / 2), 
                             enemy.getLocation().getY() - (enemy.getSize() / 2), 
                             enemy.getSize(), enemy.getSize(), true);
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
    
    //private SpriteProxy spriteProxy;
    private GameController gameController;
}
