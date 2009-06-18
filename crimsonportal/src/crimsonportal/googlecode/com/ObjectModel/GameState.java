/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveEvent;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveObserver;
import crimsonportal.googlecode.com.Observer.Player.Shoot.ShootEvent;
import crimsonportal.googlecode.com.terrain.InvalidTerrainException;
import crimsonportal.googlecode.com.terrain.Terrain;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dagwud
 */
public class GameState implements PlayerMoveObserver
{
    public GameState()
    {
        players = new LinkedList<PlayerUnit>();
        enemies = new LinkedList<EnemyUnit>();
        pickups = new LinkedList<Pickup>();
        bullets = new LinkedList<Bullet>();
        elapsedGameTime = new GameTime(true);
        map = new Map();
    }
    
    public void loadTerrain(String terrainFilename) throws InvalidTerrainException {
        terrain = new Terrain(513, 513);
        try {
            terrain.loadTerrain(terrainFilename);
        }
        catch (Exception e) {
            throw new InvalidTerrainException(e.getMessage());
        }
        
    }
    
    public Terrain getTerrain() {
        return terrain;
    }
    
    public Iterator<PlayerUnit> getPlayers()
    {
        return players.iterator();
    }
    
    public int getNumPlayers()
    {
        return players.size();
    }
    
    public Iterator<EnemyUnit> getEnemies()
    {
        return enemies.iterator();
    }
    
    public Iterator<Bullet> getBullets()
    {
        return bullets.iterator();
    }
    
    public int getNumBullets()
    {
        return bullets.size();
    }
    
    protected void spawnEnemy(EnemyUnit enemyUnit)
    {
        enemies.add(enemyUnit);
    }
    
    protected void killEnemy(EnemyUnit enemy)
    {
        enemies.remove(enemy);
    }
    
    public int getNumEnemies()
    {
        return enemies.size();
    }
    
    public Iterator<Pickup> getPickups()
    {
        return pickups.iterator();
    }
    
    public synchronized Iterator<GameObject> getGameObjects()
    {
        Collection<GameObject> gameObjects = new LinkedList<GameObject>(pickups);
        gameObjects.addAll(enemies);
        gameObjects.addAll(bullets);
        gameObjects.addAll(players);
        return gameObjects.iterator();
    }
            
    
    protected void spawnPlayer(Location location)
    {
        PlayerUnit player = new PlayerUnit(location, 4, this);
        players.add(player);
        player.addObserver(this);
    }
    
    protected void spawnPickup()
    {
        Location location = Pickup.generateLocation(map);
        location = getTerrain().convertTerrainToMapLocation(getTerrain().highestY, 
                getTerrain().highestX, map);
        
        Weapon weapon = new Weapon(10, 1);
        WeaponPickup pickup = new WeaponPickup(5.0, location, 
                                                new GameTime(elapsedGameTime), 
                                                weapon);
        pickups.add(pickup);
    }
    
    public GameTime getGameTime()
    {
        return elapsedGameTime;
    }
    
    public Map getMap()
    {
        return map;
    }
    
    private Collection<PlayerUnit> players;
    private Collection<EnemyUnit> enemies;
    private Collection<Pickup> pickups;
    private Collection<Bullet> bullets;
    private GameTime elapsedGameTime;
    private Map map;

    public void update(PlayerMoveEvent event)
    {
        PlayerUnit player = (PlayerUnit)event.getSource();
                
        Location newLocation = new Location(player.getCentreOfObject().getX() + event.getMoveAmountX(),
                player.getCentreOfObject().getY() + event.getMoveAmountY());
        player.moveTo(newLocation);
        
        // Check if the player is on a pickup:
        Iterator<Pickup> it_pickups = pickups.iterator();
        while (it_pickups.hasNext()) 
        {
            Pickup pickup = it_pickups.next();
            if (player.testOverlapsWith(pickup))
            {
                // Pickup the object:
                pickup.applyTo(player);
                it_pickups.remove();
            }
        }
    }
    
    public void update(ShootEvent event)
    {
        if (event.isStartEvent())
        {
            spawningBullets = event.getBullet();
        }
        else 
        {
            spawningBullets = null;
        }
    }
    
    public void spawnBullet()
    {
        if (spawningBullets == null)
        {
            return;
        }
        Bullet bullet = spawningBullets.clone();
        bullets.add(bullet);
    }
    
    public boolean isSpawningBullets()
    {
        return (spawningBullets != null);
    }
    
    private Bullet spawningBullets = null;
    protected Terrain terrain;
    
    public static final String landscapeName = "terrain_ice";
}
