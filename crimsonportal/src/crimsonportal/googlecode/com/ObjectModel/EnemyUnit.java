/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public abstract class EnemyUnit extends UnitWithWeapon
{
    protected EnemyUnit(double size, Location location, GameState gameState, 
            double startingHealth, int moveSpeed, GameObject target)
    {
        super(size, location, new Strategy(target), 
                gameState, null);
        this.getMovementHandler().moveSpeed = moveSpeed;
        this.health = startingHealth;
        weapon = getDefaultWeapon();
    }
    
    public abstract Weapon getDefaultWeapon();
    
    @Override
    public abstract EnemyUnit clone();
    
    protected void move() {
        getMovementHandler().move(this);
    }
    
    public void attack(Unit unitToAttack) {
        if (getWeapon() == null) {
            throw new RuntimeException(getClass().getName() + " does not have weapon set");
        }

        long timeSinceLastAttackMS = System.currentTimeMillis() - lastAttackTime;
        double attackDurationMS = 1000.0 / getWeapon().getAttackSpeed();
        if (timeSinceLastAttackMS >= attackDurationMS)
        {
            lastAttackTime = System.currentTimeMillis();
            unitToAttack.setHealth(unitToAttack.getHealth() - getWeapon().getAttackDamage());
        }
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
    
    private long lastAttackTime;

    protected static final int PRESET_MOVESPEED_AMBLE = 1;
    protected static final int PRESET_MOVESPEED_WALK = 2;
    protected static final int PRESET_MOVESPEED_TROT = 3;
    protected static final int PRESET_MOVESPEED_RACE = 4;
    protected static final double PRESET_SIZE_TINY = 10d;
    protected static final double PRESET_SIZE_SMALL = 14d;
    protected static final double PRESET_SIZE_MEDIUM = 15d;
    protected static final double PRESET_SIZE_LARGE = 20d;
    protected static final double PRESET_SIZE_HUGE = 35d;
}
