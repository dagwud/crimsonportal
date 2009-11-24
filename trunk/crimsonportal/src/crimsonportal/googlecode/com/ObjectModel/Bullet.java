/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Utils;

/**
 * Represents a bullet (that is, any bullet fired by any player which moves across
 * the game field)
 * @author dagwud
 */
public abstract class Bullet extends GameObject
{
    /**
     * Constructs a new bullet with the given minimum parameters
     * @param radius the radius of the bullet. Note that this is the logical
     * radius, and is not necessarily the same radius the bullet may be rendered by 
     * any particular GUI element
     * @param shooter the GameObject (typically, but not necessarily, a Unit) which
     * fired the bullet
     * @param location the starting location of the bullet, usually (but not necessarily)
     * the same as the location of the shooter
     * @param strategy the {@link Strategy} which defines the way in which the
     * bullet will move
     * @param moveSpeed the speed with which the bullet will travel. Note that
     * this is the logical radius, and is not necessarily the same pixel value used
     * when any particular GUI renders the bullet
     * @param attackDamage the base damage this bullet will inflict on a GameObject
     * if it collides with it
    */
    public Bullet(double radius, UnitWithWeapon shooter, Location location, 
            Strategy strategy, int attackDamage, GameTime spawnTime)
    {
        super(radius, location);
        this.strategy = strategy;
        this.moveSpeed = getDefaultMoveSpeed();
        this.attackSpeedMS = getDefaultAttackSpeed();
        this.attackDamage = attackDamage;
        this.shooter = shooter;
        this.spawnTime = spawnTime.clone();
        this.currentTime = spawnTime.clone();
        this.spawnTime.pauseTimer();
    }
    
    public long getTimeSinceSpawnMS() {
        return currentTime.getNumMilliseconds() - spawnTime.getNumMilliseconds();
    }
    
    /**
     * Returns the logical speed with which this bullet will move. Note that this
     * is independant of the pixel distance the bullet may travel when rendered
     * by any particular GUI
     * @return the movement speed of this bullet
     */
    public double getMoveSpeed()
    {
        return moveSpeed;
    }
    
    /**
     * Returns the logical speed with which thei
     * @return
     */
    public abstract double getDefaultMoveSpeed();
    
    /**
     * Returns the number of milliseconds which must elapse between bullets firing
     */
    public abstract int getDefaultAttackSpeed();
    
    protected GameTime spawnTime;
    protected GameTime currentTime;
    
    /**
     * Sets the logical speed with which this bullet will move. Note that this
     * is independant of the pixel distance the bullet may travel when rendered
     * by any particular GUI, and is relative in scale to an appropriate logical 
     * container (such as a {@link Map})
     * @param moveSpeed the new logical movement speed of this bullet
     */
    protected void setMoveSpeed(double moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }
    
    /**
     * Returns the base damage inflicted by this bullet on GameObjects it collides
     * with
     * @return this bullet's base attack damage
     * @see #attack
     */
    public int getAttackDamage()
    {
        return attackDamage;
    }
    
    /**
     * Sets the base damage which will be inflicted by this bullet on GameObjects
     * it collides with
     * @param attackDamage the new base attack damage for this bullet
     * @see #attack
     */
    protected void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }
    
    public int getAttackSpeed() {
        return attackSpeedMS;
    }
    
    /**
     * Returns the {@link Strategy} which will be used to determine the movement
     * path of this bullet
     * @return a Strategy which dictates the way in which this bullet will move
     * @see #move()
     */
    public Strategy getStrategy()
    {
        return strategy;
    }
    
    /**
     * Sets the Strategy which should be used to determine the way in which this
     * bullet moves
     * @param strategy a Strategy which will determine the way in which this bullet
     * moves
     * @see #move()
     */
    protected void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
    
    public UnitWithWeapon getShooter() {
        return shooter;
    }
    
    /**
     * Specifies the name of the filename which represents the graphical 
     * representation of this game object. 
     * Inheriting classes (i.e. bullet types) should override this method to return
     * the filenam representing the image used by GUIs to render the bullet 
     * (that is, the filename of the image which looks like the bullet)
     * <p>
     * Note that the specifics of this rendering process are not defined here, but are 
     * deferred to the presentation-related classes.
     * @return a string representing the filename which is used by GUIs to determine
     * the sprite for Bullets
     */
    public abstract String getSpriteFilename();
    
    /**
     * Moves the current bullet in a manner dictated by its current strategy.
     * This is currently implemented to mean moving in a straight line towards 
     * the target specified by the strategy
     * @see Strategy#getTarget()
     */
    public void move()
    {
        double moveAmount = moveSpeed;
        double moveAngleRadians = getRotation();
        double moveX = (double) Math.round(moveAmount * Utils.cos(moveAngleRadians));
        double moveY = (double) Math.round(moveAmount * Utils.sin(moveAngleRadians));
        
        getCentreOfObject().setX( getCentreOfObject().getX() + moveX);
        getCentreOfObject().setY( getCentreOfObject().getY() + moveY);
    }
    
    /**
     * Gets the direction which the bullet is currently facing, in radians. 
     * Overrides the {@link GameObject#getRotation()} method to keep each bullet
     * rotated towards its current target (as defined in its Strategy)
     * <p>
     * For more details on the details of the possible values returned, see the 
     * javadoc comments for {@link GameObject#rotation}
     * </p>
     * @return the angle which this bullet is facing, in radians
     * @see Strategy#getTarget
     */
    @Override
    public double getRotation()
    {
        if (rotation == 0)
        {
            double diffY = getStrategy().getTarget().getCentreOfObject().getY()
                    - this.getCentreOfObject().getY();
            double diffX = getStrategy().getTarget().getCentreOfObject().getX() 
                    - this.getCentreOfObject().getX();
            rotation = Math.atan2(diffY, diffX);
        }
        return rotation;
    }
     
    
    /**
     * Creates and returns a clone (that is, an exact copy) of the current Bullet,
     * such that the two bullets (<code>this</code> and the clone) will have the
     * identical properties but will be independant of one another.
     * @return an identical copy of the current bullet, which can be modified in
     * any way without affecting the current bullet
     */
    @Override
    public abstract Bullet clone();
    
    /**
     * The Strategy used by this bullet, used to define features such as the way
     * in which the bullet will move
     */
    protected Strategy strategy;
    
    /**
     * The logical movement speed of this bullet. Note that this value is 
     * independant of any pixel-related speed which may be rendered by a GUI
     */
    protected double moveSpeed;
    
    /**
     * The base damage which will be dealt to an object which this bullet attacks
     */
    protected int attackDamage;
    
    /**
     * The GameObject (typically, but not necessarily, a Unit) which fired this
     * bullet
     */
    protected UnitWithWeapon shooter;
    
    /**
     * The number of milliseconds which must pass between bullets being fired
     */
    protected int attackSpeedMS;

    /**
     * Causes the current bullet to 'attack' (that is, inflict damage upon) a
     * given enemy. The enemy's health will be decreased by the base attack damage
     * of this bullet. 
     * <p>
     * Note that calling this method will modify the given enemy's
     * health directly, and that the bullet itself will not be destroyed simply
     * because it attacks an enemy
     * @param enemy the enemy whose health should be reduced as a result of this
     * attack
     */
    void attack(EnemyUnit enemy)
    {
        double health = enemy.getHealth();
        health = health - attackDamage;
        enemy.setHealth(health);
    }
    
    // Preset sizes
    protected static final double PRESET_SIZE_SMALL = 2d;
    protected static final double PRESET_SIZE_MEDIUM = 4d;
    protected static final double PRESET_SIZE_LARGE = 6d;
    protected static final double PRESET_SIZE_HUGE = 8d;
    protected static final int PRESET_DAMAGE_WEAK = 1;
    protected static final int PRESET_SPEED_SLOW = 5;
    protected static final int PRESET_ATTACKSPEED_SLOW = 300;
    protected static final int PRESET_ATTACKSPEED_MEDIUM = 100;
    protected static final int PRESET_ATTACKSPEED_FAST = 50;
    protected static final double PRESET_SPEED_NORMAL = 10;
    protected static final double PRESET_SPEED_FAST = 20;
}
