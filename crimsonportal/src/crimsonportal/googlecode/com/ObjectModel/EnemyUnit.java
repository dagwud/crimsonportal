/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class EnemyUnit extends Unit
{
    protected EnemyUnit(double size, double startingHealth, int attackDamage, double attackSpeed, int moveSpeed, Location location, GameObject target, GameState gameState)
    {
        super(size, location, new Strategy(target), gameState);
        this.attackDamage = attackDamage;
        this.getMovementHandler().moveSpeed = moveSpeed;
        this.attackSpeed = attackSpeed;
        this.health = startingHealth;
    }
    
    public int getAttackDamage()
    {
        return attackDamage;
    }
    
    protected void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }
    
    public double getAttackSpeed()
    {
        return attackSpeed;
    }
    
    protected void setAttackSpeed(double attackSpeed)
    {
        this.attackSpeed = attackSpeed;
    }
    
    public void attack(PlayerUnit player)
    {
        long timeSinceLastAttackMS = System.currentTimeMillis() - lastAttackTime;
        double attackDurationMS = 1000.0 / attackSpeed;
        if (timeSinceLastAttackMS >= attackDurationMS)
        {
            lastAttackTime = System.currentTimeMillis();
            player.setHealth(player.getHealth() - attackDamage);
        }
    }
    
    @Override
    public abstract EnemyUnit clone();
    
    protected void move() {
        getMovementHandler().move(this);
    }
    
    @Override 
    public double getRotation()
    {
        double distY = getCentreOfObject().getY() - getStrategy().getTarget().getCentreOfObject().getY();
        double distX = getCentreOfObject().getX() - getStrategy().getTarget().getCentreOfObject().getX();
        double rotation = Math.atan2(distY, distX) + Math.PI; // Using PI is quicker than Math.toRadians(180);
        return rotation;
    }
    
    public abstract String getSpriteFilename();
            
    protected int attackDamage;
    protected double attackSpeed;
    private long lastAttackTime;
    
    protected static final int PRESET_MOVESPEED_AMBLE = 1;
    protected static final int PRESET_MOVESPEED_WALK = 2;
    protected static final int PRESET_MOVESPEED_TROT = 3;
    protected static final int PRESET_MOVESPEED_RACE = 4;
    protected static final int PRESET_ATTACKSPEED_SLOW = 1;
    protected static final int PRESET_ATTACKSPEED_MODERATE = 2;
    protected static final int PRESET_ATTACKSPEED_FAST = 3;
    protected static final int PRESET_ATTACKDAMAGE_ANNOY = 1;
    protected static final int PRESET_ATTACKDAMAGE_HIT = 2;
    protected static final int PRESET_ATTACKDAMAGE_HURT = 3;
    protected static final int PRESET_ATTACKDAMAGE_MAUL = 4;
    protected static final double PRESET_SIZE_TINY = 10d;
    protected static final double PRESET_SIZE_SMALL = 14d;
    protected static final double PRESET_SIZE_MEDIUM = 15d;
    protected static final double PRESET_SIZE_LARGE = 20d;
    protected static final double PRESET_SIZE_HUGE = 35d;
}
