/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Observer.Observable;
import crimsonportal.googlecode.com.Observer.PlayerMoveObserver;
import crimsonportal.googlecode.com.Observer.PlayerMoveEvent;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dagwud
 */
public class GameState extends PlayerMoveObserver
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
    
    protected void spawnEnemy(int size, int attackDamage, int moveSpeed, Location location, GameObject target)
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
    
    @Override
    public void update(Observable o, PlayerMoveEvent e)
    {
        try
        {
            PlayerUnit player = e.getPlayerToMove();
            player.getCentreOfObject().setX(player.getCentreOfObject().getX() + e.getMoveAmountX());
            player.getCentreOfObject().setY(player.getCentreOfObject().getY() + e.getMoveAmountY());
        }
        catch (ConcurrentModificationException er)
        {
            // Do nothing
        }
    }
    
    private Collection<PlayerUnit> players;
    private Collection<EnemyUnit> enemies;
    private Collection<Pickup> pickups;
    private Collection<Bullet> bullets;
    private GameTime elapsedGameTime;
    private Map map;
}
