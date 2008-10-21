/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Controller.KeyController;
import crimsonportal.googlecode.com.GameSettings.ObjectSizes;
import crimsonportal.googlecode.com.GameSettings.Timers;
import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import crimsonportal.googlecode.com.gui.GameFrame;
import crimsonportal.googlecode.com.gui.GameCanvas;
import java.awt.Dimension;
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
        super();
        // Initialise the game state: 
        gameState = new GameState();
        gameState.spawnPlayer(new Location(mapWidth / 2, mapHeight / 2));
        
        // Initialise the game GUI: 
        gameCanvas = new GameCanvas(this);
        GameFrame frame = new GameFrame(gameCanvas, gameState.getMap());
        
        // Set up the size of the frame: 
        frame.setPreferredSize(new Dimension(mapWidth, mapHeight));
        frame.pack();

        // Add a controller for player 1:
        KeyController player1Controller = new KeyController(gameState.getPlayers().next());
        frame.addKeyListener(player1Controller);
        player1Controller.addObserver(gameState);
        
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
            gameState.spawnEnemy(size, 1, moveSpeed, 
                    new Location(locationX, locationY), 
                    new Location(gameState.getPlayers().next().getLocation().getX() + (gameState.getPlayers().next().getSize() / 2),
                                    gameState.getPlayers().next().getLocation().getY() + (gameState.getPlayers().next().getSize() / 2)));
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
                            double distX = Math.abs(player.getLocation().getX() - enemy.getLocation().getX());
                            double distY = Math.abs(player.getLocation().getY() - enemy.getLocation().getY());
                            double distSquared = (distX * distX) + (distY * distY);
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
