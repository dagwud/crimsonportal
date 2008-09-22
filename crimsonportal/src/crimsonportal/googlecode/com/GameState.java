/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

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
        elapsedGameTime = new GameTime();
    }
    
    public Iterator<PlayerUnit> getPlayers()
    {
        return players.iterator();
    }
    
    public Iterator<EnemyUnit> getEnemies()
    {
        return enemies.iterator();
    }
    
    public Iterator<Pickup> getPickups()
    {
        return pickups.iterator();
    }
    
    public void spawnPlayer(Location location)
    {
        System.out.println("Spawning...");
        PlayerUnit player = new PlayerUnit(location);
        players.add(player);
    }
    
    protected Collection<PlayerUnit> players;
    protected Collection<EnemyUnit> enemies;
    protected Collection<Pickup> pickups;
    protected GameTime elapsedGameTime;
}
