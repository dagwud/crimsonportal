/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.Player.PlayerMoveEvent;
import crimsonportal.googlecode.com.Observer.Player.PlayerMoveObserver;
import crimsonportal.googlecode.com.Observer.Player.ShootEvent;
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
        map = new Map(800, 600);
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
    
    protected void spawnEnemy(double size, int attackDamage, int moveSpeed, Location location, GameObject target)
    {
        enemies.add(new EnemyUnit(size, attackDamage, moveSpeed, location, target));
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
    
    public Iterator<GameObject> getGameObjects()
    {
        Collection<GameObject> gameObjects = new LinkedList<GameObject>(pickups);
        gameObjects.addAll(enemies);
        gameObjects.addAll(bullets);
        gameObjects.addAll(players);
        return gameObjects.iterator();
    }
            
    
    protected void spawnPlayer(Location location)
    {
        PlayerUnit player = new PlayerUnit(location, 4);
        players.add(player);
        player.addObserver(this);
    }
    
    protected void spawnPickup()
    {
        Location location = Pickup.generateLocation(map);
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
        player.getCentreOfObject().setX(player.getCentreOfObject().getX() + event.getMoveAmountX());
        player.getCentreOfObject().setY(player.getCentreOfObject().getY() + event.getMoveAmountY());
    }
    
    public void update(ShootEvent event)
    {
        Bullet bullet = event.getBullet();
        bullets.add(bullet);
    }
}
