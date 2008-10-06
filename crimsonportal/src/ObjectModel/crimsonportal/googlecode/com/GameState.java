/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectModel.crimsonportal.googlecode.com;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dagwud
 */
public class GameState 
{
    public GameState()
    {
        players = new LinkedList<PlayerUnit>();
        enemies = new LinkedList<EnemyUnit>();
        pickups = new LinkedList<Pickup>();
        elapsedGameTime = new GameTime(true);
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
        PlayerUnit player = new PlayerUnit(location);
        players.add(player);
    }
    
    public GameTime getGameTime()
    {
        return elapsedGameTime;
    }
    
    private Collection<PlayerUnit> players;
    private Collection<EnemyUnit> enemies;
    private Collection<Pickup> pickups;
    private GameTime elapsedGameTime;
}
