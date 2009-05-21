/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Controller.KeyController;
import crimsonportal.googlecode.com.Controller.ShootListener;
import crimsonportal.googlecode.com.Controller.TurnListener;
import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.GameSettings.EnemyStats;
import crimsonportal.googlecode.com.GameSettings.ObjectSizes;
import crimsonportal.googlecode.com.GameSettings.Timers;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedEvent;
import crimsonportal.googlecode.com.Observer.Observable;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.ShootObserver;
import crimsonportal.googlecode.com.Observer.Player.PlayerTurnObserver;
import crimsonportal.googlecode.com.Observer.Player.ShootEvent;
import crimsonportal.googlecode.com.gui.GameFrame;
import crimsonportal.googlecode.com.gui.GameCanvas;
import java.awt.Dimension;
import java.util.Iterator;

/**
 *
 * @author dagwud
 */
public class GameController implements Observable<GameStateChangedEvent>,
                                        Runnable
{
    public GameController()
    {
        super();
        // Initialise the game state: 
        gameState = new GameState();
        gameState.spawnPlayer(new Location(mapWidth / 2, mapHeight / 2));
        gameState.spawnPickup();
        
        observers = new ObserverGroup<GameStateChangedEvent>();

        // Initialise the game GUI: 
        gameCanvas = new GameCanvas(this);
        GameFrame frame = new GameFrame(gameCanvas, gameState.getMap());
        
        // Set up the size of the frame: 
        frame.setPreferredSize(new Dimension(mapWidth, mapHeight));
        frame.pack();

        PlayerUnit controlledPlayer = gameState.getPlayers().next();
        // Add a controller for player 1:
        KeyController player1Controller = new KeyController(controlledPlayer);
        frame.addKeyListener(player1Controller);
        
        // Add a turn listener for player 1:
        TurnListener l = new TurnListener(controlledPlayer);
        gameCanvas.addMouseMotionListener(l);
        PlayerTurnObserver observer = new PlayerTurnObserver(controlledPlayer);
        l.addObserver(observer);
        
        // Add a PlayerShoot listener for player 1:
        ShootListener s = new ShootListener(controlledPlayer);
        gameCanvas.addMouseListener(s);
        gameCanvas.addMouseMotionListener(s);
        Observer<ShootEvent> obs = new Observer<ShootEvent>() {
            public void update(ShootEvent event)
            {
                gameState.update(event);
            }
        };
        s.addObserver(obs);
       
        // Start the GUI running in a separate thread, so that it does not slow
        // down this process: 
        Thread guiThread = new Thread(gameCanvas);
        guiThread.start();
        
        observers.notifyObservers(new GameStateChangedEvent(gameState));
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
        double size;
        int moveSpeed;
        int enemyType = 0;
        if (gameState.getNumEnemies() < 15) 
        {
            enemyType = EnemyStats.ENEMY_TINY;
        }
        else if (gameState.getNumEnemies() < 30)
        {
            enemyType = EnemyStats.ENEMY_SMALL;
        }
        else if (gameState.getNumEnemies() < 60)
        {
            enemyType = EnemyStats.ENEMY_MEDIUM;
        }
        else
        {
            enemyType = EnemyStats.ENEMY_LARGE;
        }
        EnemyStats enemy = EnemyStats.getEnemyStats(enemyType);
        size = enemy.getSize();
        moveSpeed = enemy.getMoveSpeed();
        
        // Choose (randomly) where to spawn the enemy:
        int side = (int) Math.round(Math.random() * 4);
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
                    gameState.getPlayers().next());
        }
        
        observers.notifyObservers(new GameStateChangedEvent(gameState));
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
                
                moveBullets(loopCount);
                
                if (loopCount % Timers.MOVE_ENEMIES == 0)
                {
                    moveEnemies();
                }
                
                if (loopCount % Timers.SPAWN_BULLET == 0) 
                {
                    if (gameState.isSpawningBullets())
                    {
                        gameState.spawnBullet();
                    }
                }
                
                observers.notifyObservers(new GameStateChangedEvent(gameState));
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
    
    public void removeAllObservers()
    {
        observers.removeAllObservers();
    }
    
    public void notifyObservers(GameStateChangedEvent event)
    {
        Debug.logMethod("GameController is notifying observers");
        observers.notifyObservers(event);
    }

    public boolean addObserver(Observer<GameStateChangedEvent> observer)
    {
        return observers.addObserver(observer);
    }

    public boolean removeObserver(Observer<GameStateChangedEvent> observer)
    {
        return observers.removeObserver(observer);
    }
    
    public int countObservers()
    {
        return observers.countObservers();
    }
    
    protected GameState gameState;
    protected GameCanvas gameCanvas;
    
    private ObserverGroup<GameStateChangedEvent> observers;
    
    private final int mapWidth = 800, mapHeight = 600;
    private int loopCount = 0;

    private void moveEnemies()
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
                double distX = Math.abs(player.getCentreOfObject().getX() - enemy.getCentreOfObject().getX());
                double distY = Math.abs(player.getCentreOfObject().getY() - enemy.getCentreOfObject().getY());
                double distSquared = (distX * distX) + (distY * distY);
                double dist = Math.sqrt(distSquared);
                if (dist - (enemy.getSize() / 2) - (player.getSize() / 2) <= 0)
                {
                    enemy.attack(player);
                }
            }
        }
    }
    
    private void moveBullets(int loopCount) 
    {
        Iterator<Bullet> bullets = gameState.getBullets();
        while (bullets.hasNext())
        {
            Bullet bullet = bullets.next();
            if (loopCount % Timers.MOVE_BULLET_PISTOL == 0)
            {
                bullet.move();
                Location loc = bullet.getCentreOfObject();
                if (loc.getX() < gameState.getMap().getOffsetX())
                {
                    bullets.remove();
                }
                else if (loc.getX() > gameState.getMap().getOffsetX() + gameState.getMap().getWidth())
                {
                    bullets.remove();
                }
                else if (loc.getY() < gameState.getMap().getOffsetY())
                {
                    bullets.remove();
                }
                else if (loc.getY() > gameState.getMap().getOffsetY() + gameState.getMap().getHeight())
                {
                    bullets.remove();
                }
                
                Iterator<EnemyUnit> enemies = gameState.getEnemies();
                while (enemies.hasNext())
                {
                    EnemyUnit enemy = enemies.next();
                    // Check if the enemy's bounding circle overlaps with
                    // the bullet's bounding circle: 
                    double distX = Math.abs(bullet.getCentreOfObject().getX() - enemy.getCentreOfObject().getX());
                    double distY = Math.abs(bullet.getCentreOfObject().getY() - enemy.getCentreOfObject().getY());
                    double distSquared = (distX * distX) + (distY * distY);
                    double dist = Math.sqrt(distSquared);
                    if (dist - (enemy.getSize() / 2) - (bullet.getSize() / 2) <= 0)
                    {
                        // Attack the enemy:
                        bullet.attack(enemy);
                        
                        // Remove the enemy if it's dead:
                        if (enemy.getHealth() <= 0)
                        {
                            enemies.remove();
                        }
                        
                        // Destroy the bullet:
                        try {
                            bullets.remove();
                        }
                        catch (IllegalStateException e) {} // Oh well!
                        break;
                    }
                }
            }
        }
    }
}
