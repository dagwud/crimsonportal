/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Controller.KeyController;
import crimsonportal.googlecode.com.Controller.ShootListener;
import crimsonportal.googlecode.com.Controller.TurnListener;
import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.Factories.EnemyUnitFactory;
import crimsonportal.googlecode.com.GameSettings.Timers;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedEvent;
import crimsonportal.googlecode.com.Observer.MenuExit.MenuListenerEvent;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.Turn.PlayerTurnObserver;
import crimsonportal.googlecode.com.Observer.Player.Shoot.ShootEvent;
import crimsonportal.googlecode.com.gui.GameFrame;
import crimsonportal.googlecode.com.gui.GameCanvas;
import crimsonportal.googlecode.com.gui.menu.MenuFactory;
import crimsonportal.googlecode.com.terrain.InvalidTerrainException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author dagwud
 */
public class GameController implements Observer<GameStateChangedEvent>,
                                        Runnable
{
    public GameController()
    {
        super();
        // Initialise the game state: 
        gameState = new GameState(this);
        
        observers = new ObserverGroup<GameStateChangedEvent>();

        try {
            gameState.loadTerrain(terrainFilename);
        }
        catch (InvalidTerrainException e) {
            System.err.println("Terrain data file " + terrainFilename + " is invalid or corrupt");
            System.exit(2);
        }
        
        // Initialise the game GUI: 
        gameCanvas = new GameCanvas(this);
        GameFrame frame = new GameFrame(gameCanvas, gameState.getMap());
        frame.initBGImage();
        
        // Set up the size of the frame: 
        frame.setPreferredSize(gameCanvas.getPreferredSize());
        frame.pack();
        
        gameState.spawnPlayer(new Location(gameState.getMap().getWidth() / 2, 
                gameState.getMap().getHeight() / 2));
        gameState.spawnPickup();
        
        PlayerUnit controlledPlayer = gameState.getPlayers().next();
        // Add a controller for player 1:
        KeyController player1Controller = new KeyController(controlledPlayer, gameState);
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
        
        gameState.addObserver(this);
        observers.notifyObservers(new GameStateChangedEvent(gameState));
        
        gameState.getMenuManager().setCanvas(frame.getLayeredPane());
        gameState.getMenuManager().openMenu(MenuFactory.MenuType.Main);
        gameState.getGameTime().pauseTimer();
        Observer<MenuListenerEvent> mnuobs = new Observer<MenuListenerEvent>() {
            public void update(MenuListenerEvent event)
            {
                if (event.getEventType() == MenuListenerEvent.Type.MENU_CLOSED) {
                    gameState.getGameTime().startTimer();
                } else {
                    gameState.getGameTime().pauseTimer();
                }
            }
        };
        gameState.getMenuManager().addObserver(mnuobs);
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
        if (Debug.checkFlag(Debug.flagKey.DISABLE_ENEMY_SPAWNING)) { return; }
        
        // Choose (randomly) where to spawn the enemy:
        int side = (int) Math.round(Math.random() * 4);
        int locationX = 100, locationY = 100;
        int mapWidth = gameState.getMap().getWidth(),
                mapHeight = gameState.getMap().getHeight();
        switch (side)
        {
            case 0:
                // Spawn on left side:
                locationX = 10;
                locationY = (int) (Math.random() * (mapHeight-10));
        break;
            case 1:
                // Spawn on top:
                locationX = (int) (Math.random() * (mapWidth-10));
                locationY = 10;
        
                break;
            case 2:
                // Spawn on right:
                locationX = mapWidth - 10;
                locationY = (int) (Math.random() * (mapHeight-10));
        
                break;
            case 3:
                // Spawn on bottom:
                locationX = (int) (Math.random() * (mapWidth-10));
                locationY = mapHeight - 10;
        
                break;
        }
        
        // Choose what type of enemy to spawn
        Location location = new Location(locationX, locationY);
        PlayerUnit target = gameState.getPlayers().next();
        if (gameState.getNumPlayers() > 0)
        {
            List<EnemyUnitFactory.enemyType> enemyTypes = new LinkedList<EnemyUnitFactory.enemyType>();
            if (gameState.getNumEnemies() < 15) 
            {
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_CRITTER);
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_ZOMBIE);
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_LEECH);
            }
            else if (gameState.getNumEnemies() < 30)
            {
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_SUPERCRITTER);
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_BARBARIAN);
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_SCUTTLER);
            }
            else
            {
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_BANSHEE);
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_FLETCHER);
                enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_LEMMINGLEADER);
            }
            
            //enemyTypes.clear();
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_CRITTER);
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_ZOMBIE);
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_LEECH);
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_SUPERCRITTER);
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_BARBARIAN);
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_SCUTTLER);
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_BANSHEE);
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_FLETCHER);
            //enemyTypes.add(EnemyUnitFactory.enemyType.ENEMY_LEMMINGLEADER);
            
            //if (gameState.getNumEnemies() > 0) return;
            
            // Choose from the list of spawnable enemy types:
            int r = random.nextInt(enemyTypes.size());
            EnemyUnit[] enemy = EnemyUnitFactory.createEnemyUnit(enemyTypes.get(r), location, target, gameState);
            for (int i = 0; i < enemy.length; i++) {
                gameState.spawnEnemy(enemy[i]);
            }
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
                // Paused
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
                if (player.testOverlapsWith(enemy))
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
                    if (dist - enemy.getRadius() - bullet.getRadius() <= 0)
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
    
    protected GameState gameState;
    protected GameCanvas gameCanvas;
    
    private ObserverGroup<GameStateChangedEvent> observers;
    
    private int loopCount = 0;
    
    String terrainFilename = "terrains/" + GameState.landscapeName + ".raw";

    private Random random = new Random(System.currentTimeMillis());
    
    public void update(GameStateChangedEvent event)
    {
        notifyObservers(event);
    }
}
