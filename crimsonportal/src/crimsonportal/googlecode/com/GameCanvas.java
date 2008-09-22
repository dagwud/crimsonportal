/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class GameCanvas extends JPanel
{
    public GameCanvas(GameController gameController)
    {
        super();
        this.gameController = gameController;
        setDoubleBuffered(true);
        drawLoop();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        try
        {
            // Draw players:
            Iterator<PlayerUnit> players = gameController.gameState.getPlayers();
            while (players.hasNext())
            {
                GameObject player = players.next();
                g.setColor(Color.green);
                g.draw3DRect(player.location.x - 5, player.location.y - 5, 10, 10, true);
            }

            // Draw enemies:
            Iterator<EnemyUnit> enemies = gameController.gameState.getEnemies();
            while (enemies.hasNext())
            {
                GameObject enemy = enemies.next();
                g.setColor(Color.red);
                g.draw3DRect(enemy.location.x - 5, enemy.location.y - 5, 10, 10, true);
            }
        }
        catch (ConcurrentModificationException e)
        {
            // Happens when one of the collections (players, enemies, etc) is being
            // updated while this thread is trying to read it. Ignore the error, 
            // since the next call to paintComponent() will repaint.
        }
    }
    
    public void drawLoop()
    {
        repaint();
    }
    
    protected SpriteProxy spriteProxy;
    protected GameController gameController;
}
