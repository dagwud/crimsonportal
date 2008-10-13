/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectModel.crimsonportal.googlecode.com;

import Controller.KeyController;
import GameSettings.crimsonportal.googlecode.com.ObjectSizes;
import GameSettings.crimsonportal.googlecode.com.Timers;
import gui.crimsonportal.googlecode.com.GameFrame;
import gui.crimsonportal.googlecode.com.GameCanvas;
import java.util.Iterator;
import java.util.Observable;

/**
 *
 * @author dagwud
 */
public class GameController extends Observable implements Runnable
{
    public GameController()
    {
        // Initialise the game state: 
        gameState = new GameState();
        gameState.spawnPlayer(new Location(mapWidth / 2, mapHeight / 2));
        
        // Initialise the game GUI: 
        gameCanvas = new GameCanvas(this);
        GameFrame frame = new GameFrame(gameCanvas);
        frame.setSize(mapWidth, mapHeight);
        frame.addKeyListener(new KeyController(gameState.getPlayers().next()));
        
        // Start the GUI running in a separate thread, so that it does not slow
        // down this process: 
        Thread guiThread = new Thread(gameCanvas);
        guiThread.start();
        
        setChanged();
        notifyObservers();
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
                            // Check if the enemy's bounding circle overlaps with
                            // the player's bounding circle: 
                            int distX = Math.abs(player.getLocation().getX() - enemy.getLocation().getX());
                            int distY = Math.abs(player.getLocation().getY() - enemy.getLocation().getY());
                            int distSquared = (distX * distX) + (distY * distY);
                            double dist = Math.sqrt(distSquared);
                            if (dist - (enemy.getSize() / 2) - (player.getSize() / 2) <= 0)
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
    
    protected GameState gameState;
    protected GameCanvas gameCanvas;
    
    private final int mapWidth = 800, mapHeight = 600;
    private int loopCount = 0;
}
