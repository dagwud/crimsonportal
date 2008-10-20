/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Observer.Observable;
import crimsonportal.googlecode.com.Observer.PlayerMoveObserver;
import crimsonportal.googlecode.com.Observer.PlayerMoveEvent;
import java.util.Collection;
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
    
    protected void spawnEnemy(int size, int attackDamage, int moveSpeed, Location location, Location target)
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
    
    protected void spawnPlayer(Location location)
    {
        PlayerUnit player = new PlayerUnit(location, 4);
        players.add(player);
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
        PlayerUnit player = e.getPlayerToMove();
        player.getLocation().setX(player.getLocation().getX() + e.getMoveAmountX());
        player.getLocation().setY(player.getLocation().getY() + e.getMoveAmountY());
        
        // Update enemies' targets:
        Iterator<EnemyUnit> i = enemies.iterator();
        while (i.hasNext())
        {
            EnemyUnit enemy = i.next();
            enemy.getStrategy().getTarget().setX(getPlayers().next().getLocation().getX() + (getPlayers().next().getSize() / 2));
            enemy.getStrategy().getTarget().setY(getPlayers().next().getLocation().getY() + (getPlayers().next().getSize() / 2));
        }
    }
    
    private Collection<PlayerUnit> players;
    private Collection<EnemyUnit> enemies;
    private Collection<Pickup> pickups;
    private GameTime elapsedGameTime;
    private Map map;
}
