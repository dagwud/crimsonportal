/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 * Represents a bullet (that is, any bullet fired by any player which moves across
 * the game field)
 * @author dagwud
 */
public class Bullet extends GameObject
{
    /**
     * Constructs a new bullet with the given minimum parameters
     * @param size the size (radius) of the bullet. Note that this is the logical
     * size, and is not necessarily the same size the bullet may be rendered by 
     * any particular GUI element
     * @param shooter the GameObject (typically, but not necessarily, a Unit) which
     * fired the bullet
     * @param location the starting location of the bullet, usually (but not necessarily)
     * the same as the location of the shooter
     * @param strategy the {@link Strategy} which defines the way in which the
     * bullet will move
     * @param moveSpeed the speed with which the bullet will travel. Note that
     * this is the logical size, and is not necessarily the same pixel value used
     * when any particular GUI renders the bullet
     * @param attackDamage the base damage this bullet will inflict on a GameObject
     * if it collides with it
    */
    public Bullet(double size, GameObject shooter, Location location, Strategy strategy, double moveSpeed, int attackDamage)
    {
        super(size, location);
        this.strategy = strategy;
        this.moveSpeed = moveSpeed;
        this.attackDamage = attackDamage;
        this.shooter = shooter;
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
    
    /**
     * Specifies the name of the filename which represents the graphical 
     * representation of this game object. 
     * For instances of the Bullet class, this returns the filename representing
     * the image used by GUIs to render a bullet (that is, the filename of the
     * image which looks like a bullet)
     * <p>
     * Note that the specifics of this rendering process are not defined here, but are 
     * deferred to the presentation-related classes.
     * @return a string representing the filename which is used by GUIs to determine
     * the sprite for Bullets
     */
    public String getSpriteFilename()
    {
        return "bullet.gif";
    }
    
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
        double moveX = (double) Math.round(moveAmount * Math.cos(moveAngleRadians));
        double moveY = (double) Math.round(moveAmount * Math.sin(moveAngleRadians));
        
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
    public Bullet clone()
    {
        Bullet bullet = new Bullet(size, shooter, shooter.getCentreOfObject(), 
                strategy.clone(), moveSpeed, attackDamage);
        return bullet;
    }
    
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
    protected GameObject shooter;

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
}
