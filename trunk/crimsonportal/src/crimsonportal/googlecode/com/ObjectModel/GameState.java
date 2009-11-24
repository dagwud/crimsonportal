package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.Factories.PickupFactory;
import crimsonportal.googlecode.com.ObjectModel.Pickups.PickupNuke;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedEvent;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedObservable;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveEvent;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveObserver;
import crimsonportal.googlecode.com.Observer.Player.Shoot.ShootEvent;
import crimsonportal.googlecode.com.gui.Animation;
import crimsonportal.googlecode.com.gui.menu.MenuManager;
import crimsonportal.googlecode.com.terrain.InvalidTerrainException;
import crimsonportal.googlecode.com.terrain.Terrain;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represents the culmination of all aspects of the current state of a game.
 * @author dagwud
 */
public class GameState implements PlayerMoveObserver, GameStateChangedObservable
{
    /**
     * Creates a new GameState. This game state will have all the relevant 
     * GameObjects collections (players, enemies, pickups, etc) as well as helper
     * class instances (such as a map, a game time and observers) initialised, but
     * it will not have its terrain loaded and the terrain must be loaded 
     * separately by a subsequent call to {@link #loadTerrain}.
     * This is done to maintain best-practices of not allowing exceptions to be
     * thrown from constructors, which could conceivably happen while loading a 
     * terrain from an external file.
     */
    public GameState(GameController gameController)
    {
        this.controller = gameController;
        players = new LinkedList<PlayerUnit>();
        enemies = new LinkedList<EnemyUnit>();
        pickups = new LinkedList<Pickup>();
        bullets = new LinkedList<Bullet>();
        PickupProxy.setGameState(this);
        animations = new LinkedList<Animation>();
        elapsedGameTime = new GameTime(true);
        Thread gameTimer = GameTimer.getInstance(elapsedGameTime);
        gameTimer.start();
        map = new Map();
        observers = new ObserverGroup<GameStateChangedEvent>();
        menuManager = new MenuManager(this);
    }
    
    /**
     * Loads a terrain model (that is, the 3D model of the terrain being used
     * in this game) from a given external file.
     * @param terrainFilename the absolute or relative name of the file to be
     * used to load the terrain. This method simply wraps a call to 
     * {@link crimsonportal.googlecode.com.terrain.Terrain#loadTerrain}
     * and so the terrain filename provided must conform to the requirements
     * dictated by that method.
     * @throws crimsonportal.googlecode.com.terrain.InvalidTerrainException if
     * the terrain file indicated by <code>terrainFilename</code> is invalid, 
     * could not be read, or does not contain a valid terrain.
     * @see crimsonportal.googlecode.com.terrain.Terrain#loadTerrain
     */
    public void loadTerrain(String terrainFilename) throws InvalidTerrainException {
        terrain = new Terrain(513, 513);
        try {
            terrain.loadTerrain(terrainFilename);
        }
        catch (Exception e) {
            throw new InvalidTerrainException(e.getMessage());
        }
        
    }
    
    /**
     * Returns the terrain which is being used for the current GameState, which
     * includes details about the physical 3D terrain over which GameObjects in 
     * this GameState are positioned
     * @return the terrain which was created for this GameState during initialization
     * and which models the 3D terrain of the environment of this GameState
     * @see #loadTerrain
     */
    public Terrain getTerrain() {
        return terrain;
    }
    
    /** 
     * Retrieves an Iterator which allows access to details of the players in this 
     * GameState. This includes all friendly and opposition human-controlled units
     * @return a templatized Iterator which will traverse through each of the players
     * in the current GameState
     */
    public Iterator<PlayerUnit> getPlayers()
    {
        return players.iterator();
    }
    
    /**
     * Returns an iterator which will iterate over all the EnemyUnits within a
     * given distance of a given location
     * @param location the location which is being used in comparison against
     * the enemy units' locations
     * @param rangeRadius the maximum distance from <code>location</code> which
     * returned enemies may be
     * @return an iterator over the collection of EnemyUnits which are within a
     * distance of <code>rangeRadius</code> of <code>location</code>
     * @see Location#getDistanceFrom
     */
    public Iterator<EnemyUnit> getEnemiesNear(Location location, double rangeRadius) {
        Collection<EnemyUnit> nearbyEnemies = new LinkedList<EnemyUnit>(enemies);
        Iterator<EnemyUnit> it_nearbyEnemies = nearbyEnemies.iterator();
        while (it_nearbyEnemies.hasNext()) {
            Unit thisEnemy = it_nearbyEnemies.next();
            double dist = location.getDistanceFrom(thisEnemy);
            dist = dist - thisEnemy.getRadius();
            if (dist > rangeRadius) {
                it_nearbyEnemies.remove();
            }
        }
        return nearbyEnemies.iterator();
    }
    
    //TODO: Implement a getCurrentPlayer() method - or should this be getCurrentPlayers()?
    
    /**
     * Returns the number of players active in the current GameState. This 
     * includes both friendly and opposition human-controlled players.
     * @return The size of the internal players Collection, which represents all
     * the players in the current GameState
     * @see #getPlayers()
     */
    public int getNumPlayers()
    {
        return players.size();
    }
    
    /** 
     * Retrieves an Iterator which allows access to details of all the enemy units
     * in this GameState. Note that this iterator will not include enemy players,
     * but will only include computer-controlled enemy units.
     * @return a templatized Iterator which will traverse through each 
     * of the enemy units in the current GameState
     * @see #getNumEnemies()
     * @see #spawnEnemy(EnemyUnit)
     */
    public Iterator<EnemyUnit> getEnemies()
    {
        return enemies.iterator();
    }
    
    /**
     * Returns the number of enemy units active in the current GameState. Note 
     * that this does not refer to all enemies (including enemy players) of the
     * current player, but rather to all computer-controlled enemy units.
     * @return the number of computer-controlled enemy units in the current
     * GameState
     * @see #getEnemies()
     */
    public int getNumEnemies()
    {
        return enemies.size();
    }
    
    /**
     * Retrieves an Iterator which allows access to details of all bullets which
     * are active in this GameState. This refers to all bullets (including those 
     * which are controlled by the all players, as well as computer-controlled
     * enemy units)
     * @return a templatized Iterator which will traverse through each of the 
     * bullets in the current GameState
     * @see #getNumBullets()
     */
    public Iterator<Bullet> getBullets()
    {
        return bullets.iterator();
    }
    
    /**
     * Returns the number of bullets which are active in this GameState. This 
     * refers to all bullets (including those which are controlled by the all 
     * players, as well as computer-controlled enemy units)
     * @return the number of bullets in the current GameState
     * @see #getBullets()
     */
    public int getNumBullets()
    {
        return bullets.size();
    }
    
    /**
     * Spawns (creates) an enemy unit in the current GameState. Immediately
     * prior to calling this method, the enemyUnit will be available through 
     * calls to methods such as {@link #getEnemies()} and {@link #getGameObjects}
     * @param enemyUnit the enemy unit to be registered with this GameState
     * @see #getEnemies()
     */
    protected void spawnEnemy(EnemyUnit enemyUnit)
    {
        enemies.add(enemyUnit);
    }
    
    /**
     * Removes an enemy unit from the current GameState. This method will not
     * affect the enemy provided, but will simply remove it from the internal
     * list of enemy unit, preventing it from being tracked further by this 
     * GameState, and thus by any classes which use this GameState to determine
     * the active enemy units. If the given EnemyUnit is not in the current GameState,
     * the method will simply return.
     * @param enemy the enemy unit to be removed from this GameState.
     * @see #getEnemies()
     * @see #spawnEnemy(EnemyUnit)
     */
    protected void killEnemy(EnemyUnit enemy)
    {
        enemies.remove(enemy);
    }
    
    protected void killUnit(Unit unit) {
        if (enemies.contains(unit)) {
            killEnemy((EnemyUnit)unit);
            return;
        }
        throw new IllegalArgumentException("Cannot kill unit type; unit is " + unit);
    }
    
    protected void killUnit(EnemyUnit victim, UnitWithWeapon benefactor) {
        if (enemies.contains(victim)) {
            killEnemy((EnemyUnit)victim);
            double currentXP = benefactor.getExperience();
            currentXP = currentXP + victim.getExperienceValueToKiller();
            benefactor.setExperience(currentXP);
            enemies.remove(victim);
            if (Debug.checkFlag(Debug.flagKey.LOGATTACKDAMAGE)) {
                Debug.logKill(victim.getEnemyTypeEnum(), 1);
            }
            return;
        }
        throw new IllegalArgumentException("Cannot kill unit type; victim unit is " + victim);
    }
    
    /**
     * Retrieves an Iterator which allows access to details of all pickups which
     * are active in this GameState. This refers to all pickups which have not been
     * collected (that is, only those that are "lying on the ground").
     * @return a templatized Iterator which will traverse through each of the 
     * bullets in the current GameState
     * @see #getNumBullets()
     */
    public Iterator<Pickup> getPickups()
    {
        return pickups.iterator();
    }
    
    public GameController getGameController() {
        return controller;
    }
    
    /**
     * Retrieves an Iterator which allows access to details of all Game Objects
     * in this GameState. This refers to all instances of each of the following
     * types of GameObject:
     * <ul>
     *   <li>Pickups (see {@link #getPickups()}</li>
     *   <li>Enemy units (see {@link #getEnemies()}</li>
     *   <li>Bullets (see {@link #getBullets()}</li>
     *   <li>Player units (see {@link #getPlayers})</li>
     * </ul>
     * @return a templatized Iterator which will traverse through each of the 
     * bullets in the current GameState
     */
    public synchronized Iterator<GameObject> getGameObjects()
    {
        Collection<GameObject> gameObjects = new LinkedList<GameObject>(pickups);
        gameObjects.addAll(enemies);
        gameObjects.addAll(bullets);
        gameObjects.addAll(players);
        return gameObjects.iterator();
    }
    
    public synchronized Iterator<Animation> getAnimations()
    {
        return animations.iterator();
    }
    
    public synchronized void addAnimation(Animation animation) {
        animations.add(animation);
    }
            
    /**
     * Spawns (creates) a PlayerUnit at a given location, and adds it to the 
     * GameState. This can be either a friendly player or an opposition-controlled player.
     * @param location the location at which the player should be created.
     * @see #getPlayers
     */
    protected void spawnPlayer(Location location)
    {
        spawnPlayer(location, null);
    }
    
    /**
     * Spawns (creates) a PlayerUnit at a given location with a given weapon, 
     * and adds it to the GameState. This can be either a friendly player or 
     * an opposition-controlled player.
     * @param location the location at which the player should be created.
     * @param weapon the weapon to be given to the player which will be created.
     * @see #getPlayers
     */
    protected void spawnPlayer(Location location, Weapon weapon)
    {
        PlayerUnit player = new PlayerUnit(location, 4, this);
        if (weapon != null) {
            player.setWeapon(weapon);
        }
        players.add(player);
        player.addObserver(this);
    }
    
    /**
     * Spawns (creates) a pickup, and adds it to the GameState. The details of 
     * the pickup which will be spawned are controlled by the PickupFactory - 
     * that is, the caller cannot choose the type or location of the pickup 
     * which will be spawned.
     */
    protected void spawnPickup()
    {
        GameTime expireTime = new GameTime(elapsedGameTime.getNumSeconds() + 5);
        
        for (int i = 0; i < 8; i++) {
            Location location = map.getRandomLocation();
            Pickup pickup = PickupFactory.createRandomPickup(location, expireTime);
            //Pickup pickup = new PickupWeaponPistol(location, expireTime);
            pickups.add(pickup);
        }
        Location location = map.getCentreOfMap();
        location.setX(location.getX() + 100);
        Pickup pickup = new PickupNuke(location, expireTime);
        pickups.add(pickup);
    }
    
    /**
     * Gets details about the current game time in this game state.
     * @return this GameState's GameTime
     */
    public GameTime getGameTime()
    {
        return elapsedGameTime;
    }
    
    /**
     * Returns the map which is being used for the current GameState, which includes 
     * details about the logical 2D environment over which GameObjects in 
     * this GameState are positioned
     * @return the Map which was created for this GameState during initialization
     * and which represents the 2D environment of this GameState
     */
    public Map getMap()
    {
        return map;
    }
    
    public void update(PlayerMoveEvent event)
    {
        PlayerUnit player = (PlayerUnit)event.getSource();
        if (event.getMoveAmountX() == 0 && event.getMoveAmountY() == 0) {
            return;
        }        
        
        Location newLocation = new Location(player.getCentreOfObject().getX() + event.getMoveAmountX(),
                player.getCentreOfObject().getY() + event.getMoveAmountY());
        player.getMovementHandler().moveTo(player, newLocation);
        
        // Check if the player is on a pickup:
        Iterator<Pickup> it_pickups = pickups.iterator();
        while (it_pickups.hasNext()) 
        {
            Pickup pickup = it_pickups.next();
            if (player.testOverlapsWith(pickup))
            {
                // Pickup the object:
                player.addPickup(pickup);
                
                // Remove the pickup from the list of available pickups:
                it_pickups.remove();
            }
        }
        notifyObservers(new GameStateChangedEvent(this));
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
        long elapsed = (elapsedGameTime.getNumMilliseconds() - lastSpawnBullet);
        if (elapsed >= spawningBullets.getAttackSpeed()) {
            lastSpawnBullet = elapsedGameTime.getNumMilliseconds();
            Bullet bullet = spawningBullets.clone();
            bullets.add(bullet);
        }
    }
    
    /**
     * Determines whether or not there is an active unit which is busy firing,
     * and by implication whether or not another bullet should be fired
     * @return true if there is a GameObject in the current GameState which is
     * firing bullets, false if there is not
     */
    public boolean isSpawningBullets()
    {
        return (spawningBullets != null);
    }
    
    public void removeAllObservers()
    {
        observers.removeAllObservers();
    }
    
    public void notifyObservers(GameStateChangedEvent event)
    {
        Debug.logMethod("GameState is notifying observers");
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
    
    public MenuManager getMenuManager() {
        return menuManager;
    }
    
    public void quit() {
        if (Debug.checkFlag(Debug.flagKey.LOGATTACKDAMAGE)) {
            // Log the total damage done before quitting:
            Debug.printLogs();
        }
        System.exit(0);
    }
    
    ObserverGroup<GameStateChangedEvent> observers;
    
    /**
     * The collection which stores the details of all Players in this GameState.
     * @see #getPlayers
     * @see #getNumPlayers
     * @see #spawnPlayer
     */
    private Collection<PlayerUnit> players;
    
    /**
     * The collection which stores the details of all computer-controlled enemies
     * in this GameState
     * @see #getEnemies
     * @see #getNumEnemies
     */
    private Collection<EnemyUnit> enemies;
    
    /**
     * The collection which stores the details of all pickups which are available
     * (that is, those which have not been collected) in the current GameState
     * @see #getPickups
     * @see #spawnPickup
     */
    private Collection<Pickup> pickups;
    
    /**
     * The collection which stores the details of all bullets which are active 
     * in the current GameState
     * @see #getBullets
     * @see #getNumBullets
     */
    private Collection<Bullet> bullets;
    
    /**
     * The collection which stores the details of all animations which are active
     * in the current GameState
     * @see #getAnimations
     */
    private Collection<Animation> animations;
    
    /**
     * The GameTime which is tracking the current GameState.
     * @see #getGameTime
     */
    private GameTime elapsedGameTime;
    
    /**
     * The map which is being used for the current GameState. This includes 
     * details about the logical 2D environment over which GameObjects in 
     * this GameState are positioned
     * @see #getMap
     */
    private Map map;
    
    /** 
     * The model of the physical 3D terrain over which GameObjects in 
     * this GameState are positioned
     * @see #getTerrain()
     */
    protected Terrain terrain;
    
    /**
     * Stores the details of the last bullet which was spawned (fired), and
     * which is also used to determine whether that same player is still firing
     * (and therefore, whether more bullets should be fired). This is done by 
     * means of the following convention: <br>
     * <code>
     *    &nbsp;&nbsp;if (spawningBullets != null) {<br>
     *    &nbsp;&nbsp;&nbsp;&nbsp;// the player is still firing; the last bullet<br>
     *    &nbsp;&nbsp;&nbsp;&nbsp;// fired is spawningBullets<br>
     *    &nbsp;&nbsp;} else {<br>
     *    &nbsp;&nbsp;&nbsp;&nbsp;// the player has stopped firing<br>
     *    &nbsp;&nbsp;}<br>
     * </code>
     */
    private Bullet spawningBullets = null;
    
    private long lastSpawnBullet = 0;
    
    /**
     * The name of the landscape to be used (which is independant of the name
     * used to load the Map)
     * TODO: Replace this with a better option than a static constant, which ties
     * together better with the name used for loading the Map
     */
    public static final String landscapeName = "terrain_peak";
    
    /**
     * The {@link GameController} which controls this GameState
     */
    protected GameController controller;
    
    /**
     * The {@link MenuManager} which controls the Menus in this GameState
     */
    protected MenuManager menuManager;
}
