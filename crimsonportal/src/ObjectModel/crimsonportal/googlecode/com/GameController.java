/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectModel.crimsonportal.googlecode.com;

import GameSettings.crimsonportal.googlecode.com.ObjectSizes;
import GameSettings.crimsonportal.googlecode.com.Timers;
import gui.crimsonportal.googlecode.com.GameFrame;
import gui.crimsonportal.googlecode.com.GameCanvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Observable;

/**
 *
 * @author dagwud
 */
public class GameController extends Observable implements Runnable, KeyListener
{
    public GameController()
    {
        gameState = new GameState();
        gameState.spawnPlayer(new Location(mapWidth / 2, mapHeight / 2));
        gameCanvas = new GameCanvas(this);
        
        GameFrame frame = new GameFrame(gameCanvas);
        frame.setSize(mapWidth, mapHeight);
        
        // Start the GUI running in a separate thread, so that it does not slow
        // down this process: 
        Thread t = new Thread(gameCanvas);
        t.start();
    }
    
    public GameState getGameState()
    {
        return gameState;
    }
    
    public GameCanvas getGameCanvas()
    {
        return gameCanvas;
    }
    
    public void spawnEnemy()
    {
        // Determine the size of the new enemy to spawn:
        // Note: this will be replaced with a factory later
        int size, moveSpeed;
        if (gameState.getNumEnemies() < 15) 
        {
            size = ObjectSizes.ENEMY_SIZE_TINY;
            moveSpeed = 1;
        }
        else if (gameState.getNumEnemies() < 30)
        {
            size = ObjectSizes.ENEMY_SIZE_SMALL;
            moveSpeed = 2;
        }
        else if (gameState.getNumEnemies() < 60)
        {
            size = ObjectSizes.ENEMY_SIZE_LARGE;
            moveSpeed = 3;
        }
        else
        {
            size = ObjectSizes.ENEMY_SIZE_HUGE;
            moveSpeed = 5;
        }
        
        // Choose (randomly) where to spawn the enemy:
        int side = (int) Math.floor(Math.random() * 4);
        int locationX = 100, locationY = 100;
        switch (side)
        {
            case 0:
                // Spawn on left side:
                locationX = 0;
                locationY = (int) (Math.random() * mapWidth);
                break;
            case 1:
                // Spawn on top:
                locationX = (int) (Math.random() * mapWidth);
                locationY = 0;
                break;
            case 2:
                // Spawn on right:
                locationX = mapWidth;
                locationY = (int) (Math.random() * mapHeight);
                break;
            case 3:
                // Spawn on bottom:
                locationX = (int) (Math.random() * mapWidth);
                locationY = mapHeight;
                break;
        }
        
        if (gameState.getNumPlayers() > 0)
        {
            gameState.spawnEnemy(size, 1, moveSpeed, new Location(locationX, locationY), gameState.getPlayers().next().getLocation());
            setChanged();
        }
        
        notifyObservers();
    }
    
    public void run()
    {
        while (true)
        {
            loopCount++;
            if (! getGameState().getGameTime().isPaused() )
            {
                if (loopCount % Timers.SPAWN_NEW_ENEMY == 0)
                {
                    if (getGameState().getNumEnemies() < 100)
                    {
                        spawnEnemy();
                    }
                }
                
                if (loopCount % Timers.MOVE_ENEMIES == 0)
                {
                    Iterator<EnemyUnit> enemies = gameState.getEnemies();
                    while (enemies.hasNext())
                    {
                        EnemyUnit enemy = enemies.next();
                        enemy.move();
                        Iterator<PlayerUnit> players = gameState.getPlayers();
                        while (players.hasNext())
                        {
                            PlayerUnit player = players.next();
                            if (enemy.getLocation().equals(player.getLocation()))
                            {
                                enemy.attack(player);
                            }
                        }
                    }
                    setChanged();
                }
                
                notifyObservers();
            }
            else
            {
                System.out.println("Paused");
            }
            
            try
            {
                Thread.sleep(1); // Allow other threads to process too!
            }
            catch (InterruptedException e)
            {
                // Do nothing
            }
        }
    }
    
    public void keyPressed(KeyEvent e) 
    {
    
        // Key Down
        if ( e.getKeyCode() == KeyEvent.VK_DOWN    )
        {
                downPressed = true;
        }
        if ( e.getKeyCode() == KeyEvent.VK_S )
        {
                downPressed = true;
        }

        // Key Up
        if ( e.getKeyCode() == KeyEvent.VK_UP )
        {
                upPressed = true;
        }
        if ( e.getKeyCode() == KeyEvent.VK_W )
        {
                upPressed = true;
        }

        //Key Left
        if (e.getKeyCode() == KeyEvent.VK_LEFT ) 
        {
                leftPressed = true;
        } 
        if (e.getKeyCode() == KeyEvent.VK_A ) 
        {
                leftPressed = true;
        } 

        //Key Right
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) 
        {
                rightPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D ) 
        {
                rightPressed = true;
        }

        // Key Fire
        if (e.getKeyCode() == KeyEvent.VK_SPACE) 
        {
                firePressed = true;
        }
    }
    public void keyReleased(KeyEvent e) 
    {
        if ( e.getKeyCode() == KeyEvent.VK_DOWN ) {
                downPressed = false;
        }
        if ( e.getKeyCode() == KeyEvent.VK_S ) {
                downPressed = false;
        }
        if ( e.getKeyCode() == KeyEvent.VK_UP ) {
                upPressed = false;
        }
        if ( e.getKeyCode() == KeyEvent.VK_W ) {
                upPressed = false;
        }
        if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
                leftPressed = false;
        }
        if ( e.getKeyCode() == KeyEvent.VK_A ) {
                leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D ) {
                rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                firePressed = false;
        }
    
    }
    public void keyTyped(KeyEvent e) 
    {
        int x = gameState.getPlayers().next().getLocation().getX();
        int y = gameState.getPlayers().next().getLocation().getY();

        switch (e.getKeyChar())
        {
            case 'w':
            case 'W':
                y--;
                break;
                
            case 'a':
            case 'A':
                x--;
                break;
                
            case 'd':
            case 'D':
                x++;
                break;
                
            case 's':
            case 'S':
                y++;
                break;
        }
        PlayerUnit p = gameState.getPlayers().next();
        p.getLocation().setX(x);
        p.getLocation().setY(y);
    }
    
    protected GameState gameState;
    protected GameCanvas gameCanvas;
    
    private final int mapWidth = 640, mapHeight = 480;
    private int loopCount = 0;
    
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;
    private boolean upPressed = false;
    private boolean firePressed = false;
}
