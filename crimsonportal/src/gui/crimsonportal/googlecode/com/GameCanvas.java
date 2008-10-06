/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.crimsonportal.googlecode.com;

import ObjectModel.crimsonportal.googlecode.com.GameController;
import ObjectModel.crimsonportal.googlecode.com.PlayerUnit;
import ObjectModel.crimsonportal.googlecode.com.EnemyUnit;
import ObjectModel.crimsonportal.googlecode.com.GameObject;
import crimsonportal.googlecode.com.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class GameCanvas extends JPanel implements Observer
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
                g.draw3DRect(player.getLocation().getX() - 5, player.getLocation().getY() - 5, 10, 10, true);
            }

            // Draw enemies:
            Iterator<EnemyUnit> enemies = gameController.getGameState().getEnemies();
            while (enemies.hasNext())
            {
                GameObject enemy = enemies.next();
                g.setColor(Color.red);
                g.draw3DRect(enemy.getLocation().getX() - 5, enemy.getLocation().getY() - 5, 10, 10, true);
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
    
    private SpriteProxy spriteProxy;
    private GameController gameController;
}
